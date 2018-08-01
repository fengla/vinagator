package com.navi.service;

import com.navi.dto.AppDTO;
import com.navi.repository.AppRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Transactional
public class AppService {

    @Autowired
    private AppRepository appRepository;

    public AppDTO save(AppDTO appDTO){
        return appRepository.save(appDTO);
    }

    public List<AppDTO> findAll(){
        return appRepository.findAll();
    }

    //此方法可以查询所有结果并按照某字段排序
    @Deprecated
    public List<AppDTO> findLatestAppsOld() {
        //当前：这个是根据该字段排序，查询出所有数据。。。怎么限制查询出的条数呢？
        return  appRepository.findAll(new Sort(new Sort.Order(Sort.Direction.DESC, "updateDate")));

    }

    public Page<AppDTO> findApps(int curPage, Sort.Direction direction, String sortProperty){

        Sort sort = new Sort(direction, sortProperty);//AppDTO类中的属性名，而不是数据库中的名字（数据库中是：update_date）
        Pageable pageable = new PageRequest(curPage, 10, sort);//当前设计只展现前20个最新的app，当然这里也可以继续增加"上拉下拉的方法"
        return appRepository.findByUpdateDateNotNull(pageable);
    }

    //查询指定CT下的app信息
    public Page<AppDTO> findAppsByCT(long ct, int curPage, Sort.Direction direction, String sortProperty){

        Sort sort = new Sort(direction, sortProperty);//AppDTO类中的属性名，而不是数据库中的名字（数据库中是：update_date）
        Pageable pageable = new PageRequest(curPage, 10, sort);//每刷更新10个app的信息，后期这个可以放在配置文件中
        return appRepository.findByCt(ct, pageable);
    }

    //搜索
    //todo：搜索接口每一刷显示几条信息？与普通页面展示量一样吗？还是一下子展示所有结果？（看美团外卖的实现，并不是一下子展示所有，而是上拉加载更多来实现的）
    public Page<AppDTO> searchApps(String keyword, int curPage, Sort.Direction direction, String sortProperty){

        Sort sort = new Sort(direction, sortProperty);
        Pageable pageable = new PageRequest(curPage, 10, sort);
        return appRepository.findByKeywordContaining("%"+keyword+"%", pageable);//模糊匹配的两个%
    }

    public AppDTO findById(Long appId){
        return appRepository.findByAppid(appId);
    }

    public AppDTO findByIcon(String icon){
        return appRepository.findByIcon(icon);
    }

    public AppDTO findByQrCode(String qrcode){
        return appRepository.findByQrCode(qrcode);
    }

    public boolean auditByAppid(boolean valid, long appid){
        try {
            appRepository.auditByAppid(valid, appid);
        }catch(Exception e){
            log.error("auditByAppid failed", e);
            return false;
        }
        return true;
    }


    public boolean updateCtByAppid(int ct, long appid){
        try {
            appRepository.updateCtByAppid(ct, appid);
        }catch(Exception e){
            log.error("updateCtByAppid failed", e);
            return false;
        }
        return true;
    }


}
