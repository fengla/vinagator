package com.navi.repository;

import com.navi.dto.CtDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface CtRepository extends CrudRepository<CtDTO, Long>{


    public CtDTO save(CtDTO ctDTO);//添加一级分类

    //关于这种方法自动生成机制还是不明白？
    //分别针对增删改查学习这种机制。。。

    public List<CtDTO> findAll();//查询所有一级分类

//    public List<CtDTO> findAllByDateAfter(long date);//查询当前日期之后的所有活动

    public CtDTO findById(Long id);

    public Page<CtDTO> findByUpdateDateNotNull(Pageable pageable);
}
