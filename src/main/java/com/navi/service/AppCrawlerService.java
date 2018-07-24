package com.navi.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.navi.dto.AppDTO;
import com.navi.dto.PreviewDTO;
import com.navi.repository.AppRepository;
import com.navi.util.AppUtil;
import com.navi.util.FileUtil;
import com.navi.util.ImgUtil;
import com.navi.util.JsonSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContext;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class AppCrawlerService {

    @Autowired
    private AppRepository appRepository;

    @Autowired
    ServletContext context;

    @Autowired
    private ImgUtil imgUtil;

    @Autowired
    private AppUtil appUtil;

    @Autowired
    private PreviewService previewService;

    public AppDTO save(AppDTO appDTO){
        return appRepository.save(appDTO);
    }

    public JSONObject parseFromResponse(String response){
        String appRaw = "";
        String regex = "xcx:(.*)\\n";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(response);
        while(m.find()){
            appRaw = m.group(1);
        }
        log.info("appRaw:" + appRaw);
        appRaw = appRaw.trim();
        appRaw = appRaw.substring(0,appRaw.length()-1);//去除最后一个逗号
        JSONObject appJson =  JSON.parseObject(appRaw);
        log.info("appJson:" + appJson);

        return appJson;
    }

    public AppDTO encapAppDTO(JSONObject appJson) throws Exception{
        AppDTO appDTO = new AppDTO();

        String title = appJson.getString("title");
        String icon = appJson.getString("icon");
        String qrcode = appJson.getString("qrcode");
        String cate1 = appJson.getString("category1");
        String cate2 = appJson.getString("category2");
        String tags = appJson.getString("tags");//tags可以写在我的summary中
        String detail = appJson.getString("summary");
        String author = appJson.getString("author");
        JSONArray screenshot = appJson.getJSONArray("screenshot");
        List<String> screenshotList = new ArrayList<String>();
        for(int i=0;i <screenshot.size(); i++){
            screenshotList.add(screenshot.getString(i));
        }
        //todo：delete later
        log.info("screenshot.size:"+ screenshot.size());
        log.info("screenshotList.size:"+ screenshotList.size());
        log.info("title:" + title);
        log.info("icon:" + icon);
        log.info("qrcode:" + qrcode);
        log.info("cate1:" + cate1);
        log.info("cate2:" + cate2);
        log.info("tags:" + tags);
        log.info("detail:" + detail);
        log.info("author:" + author);
        log.info("screenshotList:" + screenshotList);

        long appid = appUtil.genAppid();
        appDTO.setAppid(appid);

        appDTO.setDev(author);

        appDTO.setAppName(title);

        if(tags.endsWith(",")){
            tags = tags.substring(0, tags.length()-1);
        }
        appDTO.setSummary(tags);
        appDTO.setRemark(cate1 + "@@" + cate2);//备注
        appDTO.setDetail(detail);

        //图片变换（加水印，调整大小这些先不做。。。后期可以专门写一个程序对路径下所有图片文件统一处理，而不要这样在这里处理）
        //(项目优化：用户上传图片失败的时候需要提示用户具体哪个图片出错了，是因为什么原因出错了等。。。）
        //todo：下面处理各种图片。。。下载，重命名等
        //参考之前上传app的代码。。
        //todo: icon
        String uploadPath = context.getRealPath("") + "files" + File.separator;
        log.warn("uploadPath:" + uploadPath);

        String iconUploadPath = uploadPath + "icons" + File.separator;
        String qrcodeUploadPath = uploadPath + "qrcodes" + File.separator;

        //String iconName = iconFile.getOriginalFilename();
        String suffix = "";
        String[] tmp = null;
        int lastLength = 5;//取末尾lastLength+1的字符来split，从而获取后缀名

        tmp = icon.substring(icon.length()-lastLength, icon.length()).split("\\.");//获取后缀名
        if(tmp.length > 1){
            suffix = "." + tmp[1];
        }else{
            log.warn("no suffix of iconFile");
        }
        String iconName = imgUtil.genIconid(suffix);//imgUtil当时为什么没有使用静态方法来做。。。应该可以用静态方法来做这个工作的。。。
        //下载文件到本地，并且用新的名字来生成文件
        FileUtil.downloadFile(icon, iconUploadPath, iconName, suffix);
        appDTO.setIcon("/files/icons/" + iconName + suffix);

        //todo: qrcode
        //qrcode有可能不存在的
        if(qrcode!=null && !"".equals(qrcode)) {
            tmp = qrcode.substring(qrcode.length() - lastLength, qrcode.length()).split("\\.");//获取后缀名
            if (tmp.length > 1) {
                suffix = "." + tmp[1];
            } else {
                log.warn("no suffix of iconFile");
            }
            String qrcodeName = imgUtil.genIconid(suffix);//imgUtil当时为什么没有使用静态方法来做。。。应该可以用静态方法来做这个工作的。。。
            //下载文件到本地，并且用新的名字来生成文件
            FileUtil.downloadFile(qrcode, qrcodeUploadPath, qrcodeName, suffix);
            appDTO.setQrCode("/files/qrcodes/" + qrcodeName + suffix);
        }else{
            //默认qrcode图
            appDTO.setQrCode("/files/qrcodes/noqrcode.png");

        }


        //todo: preview
        String previewUploadPath = uploadPath + "previews" + File.separator;
        List<PreviewDTO> previewDTOS = new ArrayList<>();
        List<String> previews = new ArrayList<>();
        String previewName = "";
        for(String preview : screenshotList){
            tmp = preview.substring(preview.length()-lastLength, preview.length()).split("\\.");
            if(tmp.length > 1){
                suffix = "." + tmp[1];
            }else{
                log.warn("no suffix of previewFile");
            }

            if(suffix!=null && !"".equals(suffix)){
                previewName = imgUtil.genPreviewid();
                //上传文件需要保留原始后缀名
                FileUtil.downloadFile(preview, previewUploadPath, previewName, suffix);

                previews.add("/files/previews/" + previewName + suffix);
                previewDTOS.add(new PreviewDTO(previewName));
            }
        }
        appDTO.setPreviews(previews);
        JsonSerializer jsonFilter = new JsonSerializer();
        String previewStr = jsonFilter.toJson(previews);//序列化
        appDTO.setPreviewStr(previewStr);

        //previewService.save(previewDTOS);//重载的方法...todo:将预览图的名字保存下来......待释放。。。稍后需要把这个方法释放出来。。。!!!!!!!!!important
        log.warn("[debug]appName:" + title + "previewDTOS:" + previewDTOS);
        previewService.save(previewDTOS);
        //todo: 预览图end


        long updateDate = System.currentTimeMillis()/1000;//s
        appDTO.setUpdateDate(updateDate);

        log.warn("appDTO:" + appDTO);



        //图片都处理完了之后把所有最终数据封装到appDTO对象中并return


        return appDTO;
    }
}
