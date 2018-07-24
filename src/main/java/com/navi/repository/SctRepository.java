package com.navi.repository;

import com.navi.dto.SctDTO;
import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface SctRepository extends CrudRepository<SctDTO, Long>{


    public SctDTO save(SctDTO sctDTO);//添加一级分类

    public List<SctDTO> findAll();//查询所有一级分类

//    public List<SctDTO> findAllByDateAfter(long date);//查询当前日期之后的所有活动

    public SctDTO findById(Long id);
}
