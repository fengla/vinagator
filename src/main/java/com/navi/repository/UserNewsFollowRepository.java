package com.navi.repository;

import com.navi.data.UserAppFollowKey;
import com.navi.dto.UserNewsFollowDTO;
import org.springframework.data.repository.CrudRepository;

//todo: 这个主键是复合主键，所以这里是不是不应该是Long？而是应该为UserAppFollowKey？？？待验证
public interface UserNewsFollowRepository extends CrudRepository<UserNewsFollowDTO, UserAppFollowKey>{


}
