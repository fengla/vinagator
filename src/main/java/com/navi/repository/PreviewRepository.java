package com.navi.repository;

import com.navi.dto.PreviewDTO;
import org.springframework.data.repository.CrudRepository;
import java.util.List;


public interface PreviewRepository extends CrudRepository<PreviewDTO, Long>{


    //public PreviewDTO save(PreviewDTO previewDTO);

    public List<PreviewDTO> findAll();

    public PreviewDTO findById(Long id);

    public PreviewDTO findByValue(String value);

    //先不用。。后面可以再测试一下这个方法，看看是否有效
    //public List<PreviewDTO> saveAll(List<PreviewDTO> previewDTOS);//这是我自己想象出来的方法名，这里又没有提示我怎么知道对不对？
}
