package com.meetu.service;

import com.meetu.dto.UserDTO;
import com.meetu.repository.UserRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;

@Log4j
public class UserService {

    @Autowired
    private UserRepository userRepository;//这个bean需要在config配置类中进行设置吗？看上去貌似不需要，那么对于这种框架会自动处理？

    public boolean checkValid(UserDTO user){

        if(user.getEmail()==null || user.getPwd()==null){
            log.warn("userName or passWord lost");
            return false;
        }

        UserDTO userFromDB = userRepository.findUserDTOByEmail(user.getEmail());
        log.warn("userFormDB:" + userFromDB);
        if(userFromDB != null && userFromDB.getPwd().equals(user.getPwd())){
            log.warn("checkValid true");
            return true;
        }else{
            log.warn("checkValid false");
            return false;
        }
    }

    public boolean register(UserDTO user){
        log.warn("enter register, user.userName" + user.getEmail());
        boolean res = false;
        UserDTO userDTO = userRepository.save(user);
        if(userDTO!=null&& userDTO.getEmail()!=null &&!"".equals(userDTO.getEmail())){
            res = true;
        }
        log.warn("saveUserDTO res:" + res);
        return res;
    }

    public UserDTO findUserDTOByEmail(String email){
        return userRepository.findUserDTOByEmail(email);
    }

    public UserDTO findUserDTOById(Long id){
        return userRepository.findUserDTOById(id);
    }
}
