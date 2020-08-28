package com.xiaomi.service;


import com.xiaomi.mapper.LoginMapper;
import entity.UserBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utils.Md5Util;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginServiceImpl implements LoginService{


    @Resource
    private LoginMapper loginMapper;



    @Override
    @RequestMapping("/findUserInfoByAccount")
    public UserBean findUserInfoByAccount(@RequestParam String account) {
        return loginMapper.findUserInfoByAccount(account);
    }


}
