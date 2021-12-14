package com.example.servicea.feign.fallbackfactory;

import com.example.servicea.feign.ServiceB;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author Andy
 * Created time on 2021/12/14 13:44
 * @description ServiceBFallBackFactory
 */
@Component
public class ServiceBFallBackFactory implements FallbackFactory<ServiceBFallBackFactory.ServiceBWithFactory> {


    /**
     * Returns an instance of the fallback appropriate for the given cause.
     *
     * @param cause cause of an exception.
     * @return fallback
     */
    @Override
    public ServiceBWithFactory create(Throwable cause) {
        return new ServiceBWithFactory(cause);
    }



    static class ServiceBWithFactory implements ServiceB {

        private Throwable cause;

        public ServiceBWithFactory(Throwable cause){
            this.cause = cause;
        }
        @Override
        public String test(String name) {
            return String.format("name:  %s, cause: %s",name,cause);
        }
    }
}
