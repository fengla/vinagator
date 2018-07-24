package com.navi.repository;

import com.navi.dto.UserAppFollowDTO;
import org.springframework.data.repository.CrudRepository;

//attention:这里是interface而不是class
//关于范型：第一个参数是数据实体类，第二个为数据表主键的数据类型
//不同数据库extend的是不一样的，这里写的是mysql的，对于mongo的应该extends MongoRepository
public interface UserAppFollowRepository extends CrudRepository<UserAppFollowDTO, Long>{
    //todo；常规的方法（如findBy..save等。。。）这里都不需要声明？直接在service层调用就可以了？？

    //关于这种方法自动生成机制还是不明白？
    //分别针对增删改查学习这种机制。。。
}
