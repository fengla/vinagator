package com.meetu.service;

import com.meetu.dto.PreviewDTO;
import com.meetu.repository.PreviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

public class PreviewService {

    @Autowired
    private PreviewRepository previewRepository;

    //repository中的save方法可以不写出来，因为父类中早已经定义了
    //但是service中的save，saveAll还是要做区别（这里用的是方法重载），是需要显示定义出来的
    public void save(PreviewDTO previewDTO){
        previewRepository.save(previewDTO);
    }

    public void save(List<PreviewDTO> previewDTOS){
        previewRepository.save(previewDTOS);
    }

    public List<PreviewDTO> findAll(){
        return previewRepository.findAll();
    }

    public PreviewDTO findById(Long id){
        return previewRepository.findById(id);
    }
}
