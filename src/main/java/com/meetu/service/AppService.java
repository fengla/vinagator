package com.meetu.service;

import com.meetu.dto.AppDTO;
import com.meetu.repository.AppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

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
        Pageable pageable = new PageRequest(curPage, 20, sort);//当前设计只展现前20个最新的app，当然这里也可以继续增加"上拉下拉的方法"
        return appRepository.findByUpdateDateNotNull(pageable);
    }

    //查询指定CT下的app信息
    public Page<AppDTO> findAppsByCT(int ct, int curPage, Sort.Direction direction, String sortProperty){

        Sort sort = new Sort(direction, sortProperty);//AppDTO类中的属性名，而不是数据库中的名字（数据库中是：update_date）
        Pageable pageable = new PageRequest(curPage, 10, sort);//每刷更新10个app的信息，后期这个可以放在配置文件中
        return appRepository.findByCt(ct, pageable);
    }

    public AppDTO findById(Long appId){
        return appRepository.findById(appId);
    }
}
