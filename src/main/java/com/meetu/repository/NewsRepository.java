package com.meetu.repository;

import com.meetu.dto.AppDTO;
import com.meetu.dto.NewsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface NewsRepository extends CrudRepository<NewsDTO, Long>{


    public NewsDTO save(NewsDTO newsDTO);//添加一级分类

    //关于这种方法自动生成机制还是不明白？
    //分别针对增删改查学习这种机制。。。

    public List<NewsDTO> findAll();//查询所有一级分类

//    public List<NewsDTO> findAllByDateAfter(long date);//查询当前日期之后的所有活动

    public NewsDTO findById(Long id);

    Page<NewsDTO> findByTsNotNull(Pageable pageable);
}
