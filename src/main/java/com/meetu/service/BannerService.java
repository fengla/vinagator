package com.meetu.service;

import com.meetu.dto.BannerDTO;
import com.meetu.repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BannerService {

    @Autowired
    private BannerRepository ctRepository;

    public void save(BannerDTO bannerDTO){
        ctRepository.save(bannerDTO);
    }

    public List<BannerDTO> findAll(){
        return ctRepository.findAll();
    }

    public BannerDTO findById(Long bannerId){
        return ctRepository.findById(bannerId);
    }
}
