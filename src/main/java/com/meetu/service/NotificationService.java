//package com.meetu.service;
//
//import com.meetu.dto.CommentDTO;
//import com.meetu.repository.NotificationRepository;
//import lombok.extern.log4j.Log4j;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.List;
//
//@Log4j
//public class NotificationService {
//
//    @Autowired
//    private NotificationRepository notificationRepository;
//
//    public List<CommentDTO> findCommentDTOByActivityId(Long activityId){
//        return notificationRepository.findAllByNotifid(activityId);
//    }
//
//    public CommentDTO save(CommentDTO commentDTO){
//        return notificationRepository.save(commentDTO);
//    }
//}
