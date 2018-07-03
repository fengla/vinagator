package com.meetu.service;

import com.meetu.dto.CtDTO;
import com.meetu.repository.CtRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CtService {

    @Autowired
    private CtRepository ctRepository;

    public void save(CtDTO ctDTO){
        ctRepository.save(ctDTO);
    }

    public List<CtDTO> findAll(){
        return ctRepository.findAll();
    }

    public CtDTO findById(Long ctId){
        return ctRepository.findById(ctId);
    }
}
