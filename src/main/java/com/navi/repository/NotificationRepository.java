//package com.meetu.repository;
//
//import com.meetu.dto.NotificationDTO;
//import org.springframework.data.repository.CrudRepository;
//import java.util.List;
//
//public interface NotificationRepository extends CrudRepository<NotificationDTO, Long>{
//
//
//    public NotificationDTO save(NotificationDTO notificationDTO);
//
//    //public NotificationDTO findByNotifid(long notifid);
//
//    //todo：得到用户所有的通知序列化后的字符串，然后可以利用这个字符串contains查询这个用户所有的notification吗？
//    //2个需要确认的问题：1。mysql是否支持这样的contains的查询？2.这个参数该如何传到这里来？可以直接传拼接成的sql语句吗？3.这里的自定义sql查询语句到底该怎么写？
//
//}
