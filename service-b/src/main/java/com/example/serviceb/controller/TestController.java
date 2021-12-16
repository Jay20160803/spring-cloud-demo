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

        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello " + name;
    }

    @GetMapping("test3")
    public String test3(@RequestParam("name")String name){
        return "test3 " + name;
    }
}
