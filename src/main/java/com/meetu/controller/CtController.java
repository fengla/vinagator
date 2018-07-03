package com.meetu.controller;


import com.meetu.dto.CtDTO;
import com.meetu.service.CtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class CtController {

    @Autowired
    ServletContext context;//这是干吗的？还有其他什么方式实现文件上传吗？

    @Autowired
    private CtService ctService;


    /**
     * 查询cts
     */
    @GetMapping("/getAllCts")
    public Object getAllCts(){

        List<CtDTO> cts = new ArrayList<>();
        cts = ctService.findAll();

        List<List<CtDTO>> ctsResp = new ArrayList<>();
        List<CtDTO> ctsPage1 = new ArrayList<>();
        List<CtDTO> ctsPage2 = new ArrayList<>();
        List<CtDTO> ctsPage3 = new ArrayList<>();
        for(int i=0; i<8; i++){
            ctsPage1.add(cts.get(i));
        }
        for(int i=8; i<16; i++){
            ctsPage2.add(cts.get(i));
        }
        for(int i=16; i<cts.size(); i++){
            ctsPage3.add(cts.get(i));
        }
        ctsResp.add(ctsPage1);
        ctsResp.add(ctsPage2);
        ctsResp.add(ctsPage3);


        log.warn(cts.toString());


        return ctsResp;
    }



}
