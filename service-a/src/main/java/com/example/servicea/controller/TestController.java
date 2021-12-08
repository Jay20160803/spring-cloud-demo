package com.example.servicea.controller;

import com.example.servicea.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Andy
 * Created time on 2021/12/8 16:15
 * @description TODO
 */
@RestController
public class TestController {

    @Resource
    private TestService testService;

    @GetMapping("test")
    public String test(){
        return testService.test();
    }
}
