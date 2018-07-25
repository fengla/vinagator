package com.navi.repository;

import com.navi.dto.AppDTO;
import com.navi.dto.UserAppFollowDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

//attention:这里是interface而不是class
//关于范型：第一个参数是数据实体类，第二个为数据表主键的数据类型
//不同数据库extend的是不一样的，这里写的是mysql的，对于mongo的应该extends MongoRepository
public interface UserAppFollowRepository extends CrudRepository<UserAppFollowDTO, Long>{
    //todo；常规的方法（如findBy..save等。。。）这里都不需要声明？直接在service层调用就可以了？？

    //这个表本身不存储分类信息，所以需要进行join查询。。。join appDTO这个表
    //所以这里需要自己来定义sql语句
    public Page<AppDTO> findByUseridAndCt(long userid, long ct, Pageable pageable);

    //这个表自身只是存储了userid-appid的映射关系。。但是这里需要查询appDTO,所以这里还是需要自己来写join sql语句
    public Page<AppDTO> findFollowedApps(long userid, long ct, Pageable pageable);
}
