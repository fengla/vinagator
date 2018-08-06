package com.navi.repository;

import com.navi.dto.AppDTO;
import com.navi.dto.UserAppFollowDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

//attention:这里是interface而不是class
//关于范型：第一个参数是数据实体类，第二个为数据表主键的数据类型
//不同数据库extend的是不一样的，这里写的是mysql的，对于mongo的应该extends MongoRepository
public interface UserAppFollowRepository extends CrudRepository<UserAppFollowDTO, Long>{

    public UserAppFollowDTO findByUseridAndAppid(long userid, long appid);

    public int countAllByUserid(long userid);

    @Query("select a from AppDTO a where a.appid in (select ua.appid from UserAppFollowDTO ua where ua.userid =:userid)")
    public Page<AppDTO> findFollowedApps(@Param("userid") long userid, Pageable pageable);


    @Query("select a from AppDTO a where a.ct =:ct and a.appid in (select ua.appid from UserAppFollowDTO ua where ua.userid =:userid)")
    public Page<AppDTO> findFollowedAppsByCT(@Param("userid") long userid, @Param("ct") long ct, Pageable pageable);


    @Modifying
    @Query("update UserAppFollowDTO ua set ua.follow =:follow, ua.updateDate =:updateDate where ua.userid =:userid and ua.appid =:appid")
    public void unfollow(@Param("userid") long userid, @Param("appid") long appid, @Param("follow") boolean follow, @Param("updateDate") long updateDate);
    //备注：其实这个方法完全可以不写，复用save方法就可以了，因为springJPA中的save其实现就是saveOrUpdate.(当前测试是这样的，理论只是还得再确认)
}
