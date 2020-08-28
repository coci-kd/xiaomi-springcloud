package com.xiaomi.service;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xiaomi.mapper.LoginMapper;
import common.CommonConf;
import entity.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import utils.CheckSumBuilder;
import utils.HttpClientUtil;
import utils.Md5Util;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class LoginServiceImpl implements LoginService{


    @Resource
    private LoginMapper loginMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    @RequestMapping("/findUserInfoByAccount")
    public UserBean findUserInfoByAccount(@RequestParam String account) {
        return loginMapper.findUserInfoByAccount(account);
    }

    @Override
    public Map sendSms(String phone) throws Exception {
        HashMap<String, Object> result = new HashMap<String,Object>();
        //判断用户是不是在一分钟内重复获取验证码
        if (redisTemplate.hasKey(CommonConf.SMS_LOCK + phone)) {
            result.put("code", 3);
            result.put("msg", "验证码一分钟内有效，请勿重复获取！");
            return result;
        }

        //headers是请求的同步参数
        HashMap<String, Object> headers = new HashMap<String, Object>();
        //开发者平台分配的appkey
        headers.put("AppKey",CommonConf.APP_KEY );

        //	随机数（最大长度128个字符）   使用uuid生成随机码，              用 “” 替换-
        String nonce = UUID.randomUUID().toString().replaceAll("-", "");
        //String nonce = "123456";
        headers.put("Nonce",nonce);

        //当前UTC时间戳，从1970年1月1日0点0 分0 秒开始到现在的秒数(String)
        String curtime = System.currentTimeMillis()+"";
        headers.put("CurTime",curtime);

        //签名
        headers.put("CheckSum", CheckSumBuilder.getCheckSum(CommonConf.APP_SECRET, nonce, curtime));
        //params是请求的内容参数
        HashMap<String, Object> params = new HashMap<String ,Object>();
        //mobile目标手机号，
        params.put("mobile", phone);
        //templateid模板
        params.put("templateid", CommonConf.TEMPLATEID);
        //验证码
        long authCode = Math.round(Math.random()*899999+100000);
        //System.out.println(authCode);
        //String authCode = "123456";
        params.put("authCode",authCode );

        String post = HttpClientUtil.post(CommonConf.SMS_URL, params, headers);
        JSONObject parseObject = JSON.parseObject(post);
        int code = parseObject.getIntValue("code");
        if (code != 200) {
            result.put("code", 1);
            result.put("msg", "验证码发送失败！");
            return result;
        }
        //将验证码缓存到redis中，时间5分钟
        redisTemplate.opsForValue().set(CommonConf.SMS_CODE + phone, authCode, 3, TimeUnit.MINUTES);;
        //发送验证码后将用户锁定1分钟，也就是一分钟后才可以重新发送验证码
        redisTemplate.opsForValue().set(CommonConf.SMS_LOCK + phone, "lock", 60, TimeUnit.SECONDS);
        result.put("code", 0);
        result.put("msg", "发送成功！");
        return result;
    }

    @Override
    public Map sendMsg(String phone, String checkCode) {
        HashMap<String, Object> result = new HashMap<String ,Object>();

        //判断行号存在不存在
        UserBean queryUserInfoByAccount = loginMapper.queryUserInfoByAccount(phone);
        if (queryUserInfoByAccount == null) {
            result.put("code", 1);
            result.put("msg", "手机号不存在，请重新输入！");
            return result;
        }
        //判断redis中的u验证码有没有过期
        if (!redisTemplate.hasKey(CommonConf.SMS_CODE + phone)) {
            result.put("code", 2);
            result.put("msg", "验证码已过期，请重新获取！");
            return result;
        }
        //获取短信中的验证码  进行判断--------->从redis中获取
        if (!redisTemplate.opsForValue().get(CommonConf.SMS_CODE + phone).toString().equals(checkCode)) {
            result.put("code", 3);
            result.put("msg", "验证码错误，请重新输入！");
            return result;
        }
        result.put("code", 0);
        result.put("msg", "登录成功");
        return result;
    }


}
