package com.meetu.service;

import com.meetu.dto.BannerDTO;
import com.meetu.dto.CtDTO;
import com.meetu.repository.CtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class CtService {

    @Autowired
    private CtRepository ctRepository;

    public void save(CtDTO ctDTO){
        ctRepository.save(ctDTO);
    }

    public List<CtDTO> findAll(){
        return ctRepository.findAll();
    }

    public CtDTO findById(Long ctId){
        return ctRepository.findById(ctId);
    }

    public Page<CtDTO> findCts(int curPage, Sort.Direction direction, String sortProperty){

        Sort sort = new Sort(direction, sortProperty);//AppDTO类中的属性名，而不是数据库中的名字（数据库中是：update_date）
        Pageable pageable = new PageRequest(curPage, 20, sort);//当前设计只展现前20个最新的app，当然这里也可以继续增加"上拉下拉的方法"
        return ctRepository.findByUpdateDateNotNull(pageable);
    }
}
