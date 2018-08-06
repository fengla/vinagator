package com.navi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.navi.data.LoginData;
import com.navi.dto.UserDTO;
import com.navi.service.UserService;
import com.navi.util.TokenGenerator;
import com.navi.util.UserUtil;
import com.yidian.push.lib.core.util.HttpConnectionUtils;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Log4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserUtil userUtil;

    @Value("${wx.xcx.appid}")
    private String appid;


    @Value("${wx.xcx.secret}")
    private String secret;

    /**
     * 查询指定用户在本平台关注过的所有app
     */

//    @GetMapping("/profile")
//    public String profile(ModelMap modelMap, UserEase userEase){//ModelMap modelMap
//
//        log.warn("enter profile, user:" + userEase);
//        UserDTO userDTO = userService.findUserDTOByUserid(userEase.getId());
//        modelMap.addAttribute("user", userDTO);
//
//        return "profile";
//    }

    //前端执行wx.login之后就可以触发这个方法了。。
    //那么获取用户授权getUserInfo()这个方法在用户使用我的程序中有什么意义呢？难道是调用getUserInfo的时候可以补充一下gender,username这些数据？存在的意义仅仅在于此？
    @GetMapping("/register")
    public Object register(String jscode){
        //用户首次登录才会走到这里，这里基于前端传过来的js_code请求微信官方接口获取openid,unionid,session_key等数据
        //获取用户的openid后为用户在本系统注册一下（这里生成系统uid,系统token，createTime + openid等）
        //用户其他信息存储在另外一张表里面吗？还是也存储在这里？？？？
        //userDetails..???新建一个这个数据表？？

        //get openid
        Map<String, Object> params = new HashMap<>();
        params.put("appid", appid);//怎么从配置文件中获取？
        params.put("secret", secret);
        params.put("js_code", jscode);
        params.put("grant_type", "authorization_code");

        String resp = "test tmp";
        //String resp = HttpConnectionUtils.getGetResult("https://api.weixin.qq.com/sns/jscode2session?", params);
        System.out.println("resp:" + resp);

        String openid = "test_opneid";
        String unionid = "test_opneid";


        //resp->openid

        //uid token
        //怎么为用户生成token呢？

        //getUserProfile是小程序端的请求返回的数据，因此可以在调用用户首次使用本小程序之后调用getUserProfile之后再请求服务端将这个数据写到本数据库中
        // 因为用户头像，昵称等可能随时都会修改，因此可以每天晚上或者每隔几个小时轮询一下所有当天活跃用户更新一下这个数据

        long userid = userUtil.genUserid();
        UserDTO userDTONew = new UserDTO(userid);
        userDTONew.setOpenid(openid);
        userDTONew.setUnionid(unionid);
        //除了openid,unionid，用户首次使用app后还应该写那些数据到数据库中呢？

        UserDTO userDTOSaved = userService.register(userDTONew);


        return userDTOSaved;
    }

    @GetMapping("/login")
    public Object login(String jscode){

        log.info("enter login.controller, jscode:" + jscode);

        //get openid
        Map<String, Object> params = new HashMap<>();
        params.put("appid", appid);//怎么从配置文件中获取？
        params.put("secret", secret);
        params.put("js_code", jscode);
        params.put("grant_type", "authorization_code");

        String resp = "";
        try{
            resp = HttpConnectionUtils.getGetResult("https://api.weixin.qq.com/sns/jscode2session?", params);
        }catch(IOException e){
            log.error("request for openid failed, details:", e);
        }
        log.info("resp:" + resp);
        //resp:String -> json -> 获取session_key && openid
        JSONObject respJSON = JSON.parseObject(resp);
        String openid = respJSON.getString("openid");
        String session_key = respJSON.getString("session_key");
        String unionid = respJSON.getString("unionid");

        String token = "";
        //查看用户是否已注册？
        UserDTO userDTO = userService.findUserDTOByOpenid(openid);
        boolean firstLogin = false;
        if(userDTO == null){//标示用户尚未注册
            log.info("fresh_user coming...");
            firstLogin = true;
            userDTO = new UserDTO();
            //生成uid
            long userid = userUtil.genUserid();
            userDTO.setUserid(userid);
            userDTO.setOpenid(openid);
            userDTO.setSession_key(session_key);
            //userDTONew.setUnionid(unionid);

        }else{//已注册
            log.info("old_user coming...");

        }
        token = TokenGenerator.genToken();
        userDTO.setToken(token);

        long lastActive = System.currentTimeMillis()/1000;
        userDTO.setLastActive(lastActive);
        log.info("before save, userDTO:" + userDTO);

        UserDTO userDTOSaved = userService.save(userDTO);
        log.info("after login, userDTOSaved:" + userDTOSaved);

        long userid = userDTOSaved.getUserid();

        LoginData loginData = new LoginData(firstLogin, token, userid);
        log.info("after login, loginData:" + loginData);


        return loginData;
    }

    //todo:这里得用Long,Integer..如果用long,int就会报错；why ?
    //todo: 为什么post接收请求，这里需要写上@RequestParam("qrCodeFile")
    @PostMapping("/updateUserInfo4wx")
    public Object updateUserInfo4wx(@RequestParam("userid")Long userid, @RequestParam("nickName")String nickName, @RequestParam("gender")Integer gender, @RequestParam("avatar")String avatar, @RequestParam("location")String location){//gender后续是否修改成枚举类型会更合适

        log.info("enter updateUserInfo4wx, userid:" + userid);

        UserDTO userDTO = userService.findUserDTOByUserid(userid);

        if(nickName!=null && !"".equals(nickName)){
            userDTO.setNickName(nickName);
        }else{
            log.warn("nickName is null, userid:" + userid);
        }

        userDTO.setGender(gender);

        if(avatar!=null && !"".equals(avatar)){
            userDTO.setAvatar(avatar);
        }else{
            log.warn("avatar is null, userid:" + userid);
        }

        if(location!=null && !"".equals(location)){
            userDTO.setLocation(location);
        }else{
            log.warn("location is null, userid:" + userid);
        }

        userService.save(userDTO);

        return "success";
    }

    //todo: [important] 这里这些操作其实都是需要进行token验证的，这些数据都比较敏感，因此不能这么不加验证的就执行查询修改操作
    //todo：总之，用户个人数据相关的操作（增删改查）都必须进行token验证
    //todo:这里先不带token验证，写一下基本业务功能
    /**
     * 获取用户信息
     * @param userid
     * @return
     */
    @GetMapping("/getUserInfo")
    public Object getUserInfo(long userid){



        return null;
    }

    /**
     * 用户反馈
     * @param userid
     * @param content
     * @return
     */
    @PostMapping("/feedback")
    public Object feedback(@RequestParam("userid")Long userid, @RequestParam("content")String content){

        log.info("enter feedback, userid:" + userid);



        return "success";
    }


}
