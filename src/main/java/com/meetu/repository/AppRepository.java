package com.meetu.repository;

import com.meetu.dto.ActivityDTO;
import com.meetu.dto.AppDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface AppRepository extends PagingAndSortingRepository<AppDTO, Long> {


    public AppDTO save(AppDTO appDTO);//添加app

    //关于这种方法自动生成机制还是不明白？
    //分别针对增删改查学习这种机制。。。

    public List<AppDTO> findAll();//查询所有app

    //todo：这个方法查出来的是什么样子？测试一下。。。怎么查询出最新update的app
    public List<AppDTO> findAll(Sort sort);

    //public List<AppDTO> findAllByDateAfter(String date);//查询当前日期之后的所有活动

    public AppDTO findById(Long appId);//todo: 如果这是不是写的对应的数据库中列的名字这样还能识别出怎么查询吗？测试一下这个方法的使用？看看能否正常识别到？

    //todo：分页查询
    Page<AppDTO> findByUpdateDateNotNull(Pageable pageable);//NotNull这么定义方法名正确吗？

    Page<AppDTO> findByCt(int ct, Pageable pageable);//todo: 这个能按照 ct 查询么？？？有待验证
}
