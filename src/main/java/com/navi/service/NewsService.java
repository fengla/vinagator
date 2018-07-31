package com.navi.service;

import com.navi.dto.NewsDTO;
import com.navi.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public void save(NewsDTO newsDTO){
        newsRepository.save(newsDTO);
    }

    public List<NewsDTO> findAll(){
        return newsRepository.findAll();
    }

    public Page<NewsDTO> findNews(int curPage, Sort.Direction direction, String sortProperty){

        Sort sort = new Sort(direction, sortProperty);//AppDTO类中的属性名，而不是数据库中的名字（数据库中是：update_date）
        Pageable pageable = new PageRequest(curPage, 6, sort);//每一刷更新6项
        return newsRepository.findByTsNotNull(pageable);
    }

    public NewsDTO findById(String docid){
        return newsRepository.findByDocid(docid);
    }
}
