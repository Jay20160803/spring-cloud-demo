package com.example.servicea.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.example.servicea.service.TestService;
import org.springframework.stereotype.Service;

/**
 * @author Andy
 * Created time on 2021/12/8 16:49
 * @description TODO
 */
@Service
public class TestServiceImpl implements TestService {

    @SentinelResource("testMethod")
    @Override
    public String test() {
        return "ok";
    }
}
