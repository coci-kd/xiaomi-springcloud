package com.xiaomi.controller;


import com.xiaomi.service.LoginServiceFeign;
import entity.UserBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Resource
    private LoginServiceFeign loginServiceFeign;


    @RequestMapping("/ToLogin")
    public String ToLogin(){
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Map login(UserBean userBean){
        HashMap result = new HashMap();

        //查看账号是否存在
        UserBean userInfo = loginServiceFeign.findUserInfoByAccount(userBean.getAccount());
        if (userInfo == null) {
            result.put("code", 1);
            result.put("msg", "账号不存在");
            return result;
        }
        if (!userInfo.getPassword().equals(userBean.getPassword()) ) {
            result.put("code", 2);
            result.put("msg", "密码不正确，请重新输入！");
            return result;
        }
        //存session
        //session.setAttribute(session.getId(), userInfo);
        result.put("code", 0);
        result.put("msg", "登录成功");
        return result;
        }

    //toKjlogin
    @RequestMapping("/toKjlogin")
    public String toKjlogin(){
        return "kjlogin";
    }

    /**
     *@Description  发送验证码
     *@Author   kd
     *@Date  2020/08/28 19:48
     *Exception
     *
     */
    //sendSms
    @RequestMapping("sendSms")
    public Map sendSms(String phone) throws Exception{
        return loginServiceFeign.sendSms(phone);
    }

    //sendMsg
    /**
     *@Description   登录
     *@Author   kd
     *@Date  2020/08/28 20:10
     *Exception
     *
     */
    @RequestMapping("sendMsg")
    public Map sendMsg(String phone,String checkCode){
        return loginServiceFeign.sendMsg(phone,checkCode);
    }

}
