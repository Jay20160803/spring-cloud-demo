package com.example.sentinel.controller;

import com.example.sentinel.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Andy
 * Created time on 2021/12/8 9:08
 * @description TODO
 */
@RestController
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping("test")
    public String test(@RequestParam String name){
        return testService.sayHello(name);
    }
}
