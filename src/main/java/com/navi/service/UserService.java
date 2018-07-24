package com.navi.service;

import com.navi.dto.UserDTO;
import com.navi.repository.UserRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j
public class UserService {

    @Autowired
    private UserRepository userRepository;//这个bean需要在config配置类中进行设置吗？看上去貌似不需要，那么对于这种框架会自动处理？

//    public boolean checkValid(UserDTO user){
//
//        if(user.getEmail()==null || user.getPwd()==null){
//            log.warn("userName or passWord lost");
//            return false;
//        }
//
//        UserDTO userFromDB = userRepository.findUserDTOByEmail(user.getEmail());
//        log.warn("userFormDB:" + userFromDB);
//        if(userFromDB != null && userFromDB.getPwd().equals(user.getPwd())){
//            log.warn("checkValid true");
//            return true;
//        }else{
//            log.warn("checkValid false");
//            return false;
//        }
//    }

    //用户首次使用本小程序，执行register方法。。。后续运营的时候，每天对新增用户的基本信息数据进行dump出来
    public UserDTO register(UserDTO user){
        //用户刚注册时，这里是没有用户的本系统id的，只有微信openid之类的数据

        UserDTO userDTO = userRepository.save(user);

        return userDTO;
    }

    public UserDTO findUserDTOByUserid(Long userid){
        return userRepository.findUserDTOByUserid(userid);
    }


}
