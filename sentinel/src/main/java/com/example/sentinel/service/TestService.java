package com.example.sentinel.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @author Andy
 * Created time on 2021/12/8 13:52
 * @description TODO
 */
@Service
public class TestService {

    @SentinelResource(value = "sayHello")
    public String sayHello(String name){
        return "Hello, " + name;
    }
}
