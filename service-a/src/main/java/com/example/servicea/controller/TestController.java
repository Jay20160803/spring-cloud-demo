package com.example.servicea.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andy
 * Created time on 2021/12/8 16:15
 * @description TODO
 */
@RestController
public class TestController {


    @GetMapping("test")
    public String test(){
        return "ok";
    }
}
