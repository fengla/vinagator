package com.navi.util;

import com.navi.dto.AppDTO;
import com.navi.service.AppService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//todo: 当前系统到底用的是Slf4j还是Log4j
@Slf4j
public class ImgUtil {

    private static final int ICONID_LENGTH = 6;
    private static final int QRCODEID_LENGTH = 6;
    private static final int PREVIEWID_LENGTH = 6;

    private static String prefixIcon = "/files/icons/";
    //private static String suffixIcon = ""; suffix需要根据当前上传的文件来具体确定，所以这里不能公用此值

    private static String prefixQr = "/files/qrcodes/";
    //private static String suffixQr = "";

    //preview因为查重表中只保存的id，所以不需要带上前后缀进行查询

    @Autowired
    private AppService appService;

    //生成appid
    public String genIconid(String suffix){
        String res = "";
        while(true){
            res = RandomString.getString(ICONID_LENGTH);
            if(validIcon(res, suffix) == true){
                break;
            }
        }
        return res;
    }

    //生成appid
    public String genQrcodeid(String suffix){
        String res = "";
        while(true){
            res = RandomString.getString(QRCODEID_LENGTH);
            if(validQrcode(res, suffix) == true){
                break;
            }
        }
        return res;
    }

    //生成appid
    public String genPreviewid(){
        String res = "";
        while(true){
            res = RandomString.getString(PREVIEWID_LENGTH);
            if(validPreview(res) == true){
                break;
            }
        }
        return res;
    }


    //判断img.id是否在数据库中已存在(针对不同类型的图片preview,icon,qrcode等应该写不同的方法)
    //如何限制外部用户访问文件夹，直接把路径内所有数据都下载下来了？？？
    public boolean validPreview(String id){//如果分成2个表来做，怎么保证原子性？（其实另外一个表仅仅是用来做记录是否重复，并没有实际业务需要从中查询内容。。只存name，而app表中存完整路径）
        boolean valid = true;
        //todo
        //现在先用模糊查询来做？后面如果有性能平静

        return valid;
    }

    public boolean validIcon(String icon, String suffix){//suffix需要带有"."
        boolean valid = true;

        icon = prefixIcon + icon + suffix;//需要带上前后缀来进行查询是否有效
        log.warn("[debug_checkValid_icon]icon:" + icon);//todo: 实际操作看看日志有没有问题

        AppDTO appDTO = appService.findByIcon(icon);

        if(appDTO != null){
            valid = false;
        }

        return valid;
    }

    public boolean validQrcode(String qrcode, String suffix){
        boolean valid = true;

        qrcode = prefixQr + qrcode + suffix;
        log.warn("[debug_checkValid_qrcode]qrcode:" + qrcode);

        AppDTO appDTO = appService.findByQrCode(qrcode);

        if(appDTO != null){
            valid = false;
        }

        return valid;
    }

    //icon，qrCode可以直接在 appDTO表中查询
    //preview是拼接字符串的？这个怎么查询呢？用模糊查询？like %str%这样来检索appDTO.previewStr这个字段？
}
