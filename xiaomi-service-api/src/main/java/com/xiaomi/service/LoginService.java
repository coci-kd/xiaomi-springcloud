package com.xiaomi.service;


import entity.UserBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
public interface LoginService {

    @RequestMapping("/findUserInfoByAccount")
    UserBean findUserInfoByAccount(@RequestParam String account);
}
