package com.xiaomi.service;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "xiaomi-provider", fallback = LoginServiceFallBack.class)
public interface LoginServiceFeign extends LoginService{

}
