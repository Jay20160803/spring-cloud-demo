package com.example.serviceb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andy
 * Created time on 2021/12/10 16:21
 * @description TODO
 */
@RestController
public class TestController {

    @GetMapping("test")
    public String test(@RequestParam("name")String name){
        return "hello " + name;
    }
}
