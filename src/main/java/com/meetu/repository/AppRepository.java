package com.meetu.repository;

import com.meetu.dto.AppDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface AppRepository extends PagingAndSortingRepository<AppDTO, Long> {


    public AppDTO save(AppDTO appDTO);//添加app

    //关于这种方法自动生成机制还是不明白？
    //分别针对增删改查学习这种机制。。。

    public List<AppDTO> findAll();//查询所有app

    //todo：这个方法查出来的是什么样子？测试一下。。。怎么查询出最新update的app
    public List<AppDTO> findAll(Sort sort);

    //public List<AppDTO> findAllByDateAfter(String date);//查询当前日期之后的所有活动

    public AppDTO findByAppid(Long appId);//todo: 如果这是不是写的对应的数据库中列的名字这样还能识别出怎么查询吗？测试一下这个方法的使用？看看能否正常识别到？

    public AppDTO findByIcon(String icon);
    public AppDTO findByQrCode(String qrcode);

    //todo：分页查询
    public Page<AppDTO> findByUpdateDateNotNull(Pageable pageable);//NotNull这么定义方法名正确吗？

    public Page<AppDTO> findByCt(int ct, Pageable pageable);//todo: 这个能按照 ct 查询么？？？有待验证

    public Page<AppDTO> findAllByAppNameLike(String keyword, Pageable pageable);//todo: 这个能按照 ct 查询么？？？有待验证

    //原来JPA中自定义sql查询这么做就可以了啊...
    //todo: 现在是name,summary,detail都检索，这样会不会比较慢？后期不应该带上detail进行检索？？？
    @Query("select appDTO from AppDTO appDTO where appDTO.appName like :keyword or appDTO.summary like :keyword or appDTO.detail like :keyword")
    public Page<AppDTO> findByKeywordContaining(@Param("keyword") String keyword, Pageable pageable);
}
