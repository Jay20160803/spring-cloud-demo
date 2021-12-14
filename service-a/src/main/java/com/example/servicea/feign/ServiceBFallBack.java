package com.example.servicea.feign;

import org.springframework.stereotype.Component;

/**
 * @author Andy
 * Created time on 2021/12/10 16:19
 * @description ServiceB 服务接口
 */
@Component
public class ServiceBFallBack implements ServiceB {


    @Override
    public String test(String name) {
        return "error";
    }
}
