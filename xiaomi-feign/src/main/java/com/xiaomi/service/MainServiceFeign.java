package com.xiaomi.service;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "xiaomi-provider", fallback = MainServiceFallBack.class)
public interface MainServiceFeign extends MainSevice {
}
