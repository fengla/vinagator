package com.meetu.repository;

import com.meetu.dto.UserDTO;
import org.springframework.data.repository.CrudRepository;

//attention:这里是interface而不是class
//关于范型：第一个参数是数据实体类，第二个为数据表主键的数据类型
//不同数据库extend的是不一样的，这里写的是mysql的，对于mongo的应该extends MongoRepository
public interface UserRepository extends CrudRepository<UserDTO, Long>{

    public UserDTO findUserDTOByEmail(String email);

    public UserDTO findUserDTOById(Long id);

    public UserDTO save(UserDTO userDTO);//todo: 用户注册

    //关于这种方法自动生成机制还是不明白？
    //分别针对增删改查学习这种机制。。。
}
