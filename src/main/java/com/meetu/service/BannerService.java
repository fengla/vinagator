package com.meetu.service;

import com.meetu.dto.AppDTO;
import com.meetu.dto.BannerDTO;
import com.meetu.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class BannerService {

    @Autowired
    private BannerRepository bannerRepository;

    public void save(BannerDTO bannerDTO){
        bannerRepository.save(bannerDTO);
    }

    public List<BannerDTO> findAll(){
        return bannerRepository.findAll();
    }

    public BannerDTO findById(Long bannerId){
        return bannerRepository.findById(bannerId);
    }

    public Page<BannerDTO> findBanners(int curPage, Sort.Direction direction, String sortProperty){

        Sort sort = new Sort(direction, sortProperty);//AppDTO类中的属性名，而不是数据库中的名字（数据库中是：update_date）
        Pageable pageable = new PageRequest(curPage, 6, sort);//当前设计只展现前20个最新的app，当然这里也可以继续增加"上拉下拉的方法"
        return bannerRepository.findByUpdateDateNotNull(pageable);
    }
}
