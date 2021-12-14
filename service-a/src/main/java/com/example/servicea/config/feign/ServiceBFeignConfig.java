package com.example.servicea.config.feign;


import org.springframework.context.annotation.Bean;

/**
 * @author Andy
 * Created time on 2021/12/14 11:09
 * @description FeignConfig
 */
public class ServiceBFeignConfig {

    @Bean
    public feign.Logger.Level logger(){
       return feign.Logger.Level.FULL;
    }

}
