package com.navi.repository;

import com.navi.data.UserNewsFollowKey;
import com.navi.dto.UserNewsFollowDTO;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//todo: 这个主键是复合主键，所以这里是不是不应该是Long？而是应该为UserAppFollowKey？？？待验证
public interface UserNewsFollowRepository extends CrudRepository<UserNewsFollowDTO, UserNewsFollowKey>{

    //这样子能count出来吗？
    //这里形参书写的顺序需要与方法名中使用的顺序一致
    //这里形参的名字无所谓（不要求与DTO中的一致）
    public int countAllByNewsidAndFollow(String newsid, int follow);

    public UserNewsFollowDTO findByUseridAndNewsid(long userid, String newsid);

    //查询用户新闻点赞关系
    public List<UserNewsFollowDTO> findByUseridAndNewsidIn(long userid, List<String> newsids);
}
