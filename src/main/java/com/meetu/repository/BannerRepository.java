package com.meetu.repository;

import com.meetu.dto.BannerDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface BannerRepository extends CrudRepository<BannerDTO, Long>{


    public BannerDTO save(BannerDTO bannerDTO);//添加一级分类

    //关于这种方法自动生成机制还是不明白？
    //分别针对增删改查学习这种机制。。。

    //todo: 后期应该是会有很多Banner...所以这里应该提供一个方法只查询当前有效的Banner..而不仅仅是当前的这个查询所有Banner的方法
    public List<BannerDTO> findAll();//查询所有Banner

//    public List<BannerDTO> findAllByDateAfter(long date);//查询当前日期之后的所有活动

    public BannerDTO findById(Long id);
}
