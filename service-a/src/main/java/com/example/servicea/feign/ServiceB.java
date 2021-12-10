package com.example.servicea.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Andy
 * Created time on 2021/12/10 16:19
 * @description ServiceB 服务接口
 */
@FeignClient(name = "service-b")
public interface ServiceB {

    @GetMapping("test")
    String test(@RequestParam("name")String name);
}
