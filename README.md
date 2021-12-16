springboot + nacos + sentinel + gateway + openFeign + spring cloud LoadBalancer  + Security + Sleuth

服务注册与发现:
限流降级：
网关:
- [spring boot/spring cloud/spring cloud alibaba 版本关系](https://github.com/alibaba/spring-cloud-alibaba/wiki/%E7%89%88%E6%9C%AC%E8%AF%B4%E6%98%8E)


# service-a/service-b:
# 1、整合nacos 服务注册与发现 已完成
- 1.1 健康监测添加 未完成
# 2、整合nacos 配置中心 已完成

# 3、sentinel + sentinel Dashboard

- [sentinel 文档](https://github.com/alibaba/spring-cloud-alibaba/wiki/Sentinel)
    
## 一: 应用服务 + sentinel core
- 1、[动态数据源支持,push模式(sentinel + nacos)](https://github.com/alibaba/Sentinel/wiki/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%99%E6%89%A9%E5%B1%95)
- 2、[Sentinel 注解支持]()
- 3、[而在 Web 层直接使用 Spring Cloud Alibaba 自带的 Web 埋点适配]()
## 二: 生产环境的 sentinel Dashboard
- 1、[规则控制集中配置(sentinel Dashboard + nacos)](https://github.com/alibaba/Sentinel/wiki/Sentinel-%E6%8E%A7%E5%88%B6%E5%8F%B0%EF%BC%88%E9%9B%86%E7%BE%A4%E6%B5%81%E6%8E%A7%E7%AE%A1%E7%90%86%EF%BC%89#%E8%A7%84%E5%88%99%E9%85%8D%E7%BD%AE)
```
java -Dserver.port=8847 -Dcsp.sentinel.dashboard.server=localhost:8847 -Dproject.name=sentinel-dashboard -Dnacos.serverAddr=119.3.123.94:8848 -jar sentinel-dashboard-20211209.jar
```
- 2、[监控，支持可靠、快速的实时监控和历史监控数据查询、部署多个控制台实例时，通常需要将规则存至 DB 中，规则变更后同步向配置中心推送规则。]()
- 3、[权限控制]()
- 4、[查看机器列表以及健康情况]() 
- 5、[集群流控]()
       
# 4、openFeign
1、[文档](https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/)
- 依赖
```
spring-cloud-starter-openfeign

Spring Cloud Netflix Ribbon 现在处于维护模式，因此我们建议改用 Spring Cloud LoadBalancer
spring-cloud-starter-loadbalancer
```
2、 声明式REST客户端：Feign
```
1、可插入的注释支持
2、可插拔的编码器和解码器
3、集成了Ribbon和Eureka,Spring cloud CircuitBreaker来提供使用Feign时负载均衡的http客户端
```  
2.1 如何包含Feign
```
1、@EnableFeignClients 启动类上注解
2、@FeignClient 客户端接口注解
3、设置服务注册列表获取（待确定）
4、设置客户端负载均衡（待确定）
5、设置熔断与服务降级（待确定）
```
2.2 覆盖 Feign 默认值
- 默认feign配置
```
FeignClientsConfiguration 中
DecoderfeignDecoder: ResponseEntityDecoder( 包装了一个SpringDecoder)
Encoder feign编码器： SpringEncoder
Logger 伪装记录器： Slf4jLogger
Contract 假合同： SpringMvcContract
Feign.Builder feignBuilder： HystrixFeign.Builder
Feign.Builder feignBuilder： FeignCircuitBreaker.Builder
ClientfeignClient：如果 Ribbon 在类路径中并启用它是一个LoadBalancerFeignClient，否则如果 Spring Cloud LoadBalancer 在类路径中，FeignBlockingLoadBalancerClient则使用。如果它们都不在类路径中，则使用默认的伪装客户端
```
- 没有的feign配置
```
Logger.Level
Retryer: Retryer.NEVER_RETRY 这将禁用重试
ErrorDecoder
Request.Options
Collection<RequestInterceptor>
SetterFactory
QueryMapEncoder
```
- 创建一种类型的bean 并将其放置在 @FeignClient 中 允许您覆盖所描述的每个 bean
```
不需要用@Configuration,不让会成全局配置（例如 ServiceBFeignConfig）
```
- @FeignClient 也可以使用配置属性进行配置
```
配置feignName 客户端

feign:
  client:
    config:
      feignName:
        connectTimeout: 5000
        readTimeout: 5000
        loggerLevel: full
        errorDecoder: com.example.SimpleErrorDecoder
        retryer: com.example.SimpleRetryer
       defaultQueryParameters:
          query: queryValue
        defaultRequestHeaders:
          header: headerValue
        requestInterceptors:
          - com.example.FooRequestInterceptor
          - com.example.BarRequestInterceptor
        decode404: false
        encoder: com.example.SimpleEncoder
        decoder: com.example.SimpleDecoder
        contract: com.example.SimpleContract
                
配置所有客户端
feign:
   config:
      default:
         connectTimeout: 5000
feign:
  sentinel:
    enabled: true
  okhttp:
    enabled: true
 
如果我们同时创建@Configurationbean 和配置属性，配置属性将获胜。它将覆盖@Configuration值              
```
2.3 SpringEncoder 配置

3、 超时处理
```
OpenFeign 使用两个超时参数：
connectTimeout: 防止由于服务器处理时间长而阻塞调用者
readTimeout 从连接建立时开始应用，在返回响应时间过长时触发
```
4、手动创建Feign客户端
```
在某些情况下，可能需要以使用上述方法无法实现的方式自定义您的 Feign Client。
在这种情况下，您可以使用Feign Builder API创建客户端 。
```
5、Feign Spring Cloud 断路器支持
```
如果 Spring Cloud CircuitBreaker 在 classpath 
和 上feign.circuitbreaker.enabled=true，Feign 将使用断路器包装所有方法。

SentinelCircuitBreakerAutoConfiguration 在整合Sentinel后会生成CircuitBreaker 实例
```

5、Feign Spring Cloud 断路器fallback
```
当电路打开或出现错误时执行的默认代码路径。
要为给定的@FeignClient设置启用回退，请将fallback属性设置为实现回退的类名

如果需要访问触发回退的原因，则可以使用fallbackFactory内部的属性@FeignClient  ???
```

6、Feign 和 @Primary
```
Spring Cloud OpenFeign 将所有 Feign 实例默认标记为@Primary
```

7、Feign 支持继承

8、Feign 请求/响应压缩

9、feign 日志 (未能成功)

10、Feign @QueryMap 支持
```
Spring Cloud OpenFeign 提供了一个等效的@SpringQueryMap注解，
用于将 POJO 或 Map 参数注解为查询参数映射
如果您需要对生成的查询参数映射进行更多控制，则可以实现自定义QueryMapEncoderbean
```
11、[feign 常见的属性](https://docs.spring.io/spring-cloud-openfeign/docs/2.2.10.RELEASE/reference/html/appendix.html)

# 4、Spring Cloud Gateway
1、 添加依赖
```
spring-cloud-starter-gateway
```
2、配置predicate、filter
```
快捷方式配置:
快捷方式配置由过滤器名称识别，后跟等号 ( =)，后跟由逗号 ( ,)分隔的参数值。
spring:
  cloud:
    gateway:
      routes:
      - id: after_route
        uri: https://example.org
        predicates:
        - Cookie=mycookie,mycookievalue

```
3、路由predicates-factories
```
- After=2017-01-20T17:42:47.789-07:00[America/Denver]
- Before=2017-01-20T17:42:47.789-07:00[America/Denver]
- Between=2017-01-20T17:42:47.789-07:00[America/Denver], 2017-01-21T17:42:47.789-07:00[America/Denver]
- Cookie=name, regexp
- Header=name, regexp
- Host=**.somehost.org,**.anotherhost.org
- Method=GET,POST
- Path=/red/{segment},/blue/{segment}
- Path=/service/**
- Query=name, regexp
- RemoteAddr=192.168.1.1/24
- Weight=group1, 2
```
4、路由 filter-factories
```
filters:
- AddRequestHeader=name, value
- AddRequestParameter=name, value
- AddResponseHeader=name, value
- PrefixPath=/prefix
- RedirectTo=status, url
- RemoveRequestHeader=X-Request-Foo
- RemoveResponseHeader=X-Response-Foo
- RemoveRequestParameter=name
- RewritePath=/red(?<segment>/?.*), $\{segment}
- SetPath=/{segment}
- SetRequestHeader=name, value
- SetResponseHeader=name, value
- SetStatus=status
- StripPrefix=parts
- name: Retry
- name: RequestSize

默认过滤器（要添加过滤器并将其应用于所有路由，您可以使用spring.cloud.gateway.default-filters）
```
5、global-filters(全局过滤器)
```
已自动注入全局过滤器
@Bean
public GlobalFilter customFilter() {
    return new CustomGlobalFilter();
}

public class CustomGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("custom global filter");
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -1;
    }
}

1、组合global-filters和GatewayFilter排序
按org.springframework.core.Ordered接口排序的

2、ForwardRoutingFilter
forward:///localendpoint

3、LoadBalancerClientFilter
lb://myservice

4、ReactiveLoadBalancerClientFilter
lb://myservice

5、Netty 路由过滤器
如果位于ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR交换属性中的 URL具有http或https方案，
则 Netty 路由过滤器运行。它使用 NettyHttpClient进行下游代理请求。
响应被放入ServerWebExchangeUtils.CLIENT_RESPONSE_ATTR交换属性中以供稍后过滤器使用。
（还有一个实验WebClientHttpRoutingFilter可以执行相同的功能但不需要 Netty。）

6、NettyWriteResponseFilter

7、routetorequesturl-filter

8、websocket-routing-filter
```

5、HttpHeadersFilters
```
1、forwarded-headers-filter
2、removehopbyhop-headers-filter
3、xforwarded-headers-filter
```
6、tls-and-ssl
```
网关可以通过遵循通常的 Spring 服务器配置来侦听 HTTPS 上的请求
server:
  ssl:
    enabled: true
    key-alias: scg
    key-store-password: scg1234
    key-store: classpath:scg-keystore.p12
    key-store-type: PKCS12
```
7、配置
```
PropertiesRouteDefinitionLocator
```
8、Http超时配置
```
1、全局超时
spring:
  cloud:
    gateway:
      httpclient:
        connect-timeout: 1000
        response-timeout: 5s
2、每个路由超时
      - id: per_route_timeouts
        uri: https://example.org
        predicates:
          - name: Path
            args:
              pattern: /delay/{timeout}
        metadata:
          response-timeout: 200
          connect-timeout: 200            
```
9、discoveryclient-route-definition-locator
```
为所有的服务注册中的service创建默认的路径匹配与过滤器匹配
默认的路径匹配：
id: serviceId
lb://serviceId
predicates:
- Path=/serviceId/**

默认的重写路径过滤器，$\{remaining} 替换 /serviceId(?<remaining>/.*)
filters：
- RewritePath=/serviceId(?<remaining>/.*), $\{remaining}
```
10、reactor-netty-access-logs (Reactor Netty 访问日志)
```
Java 属性中设置：
-Dreactor.netty.http.server.accessLogEnabled=true

logback.xml 中设置：

 <appender name="accessLog" class="ch.qos.logback.core.FileAppender">
        <file>access_log.log</file>
        <encoder>
            <pattern>%msg%n</pattern>
        </encoder>
    </appender>
    <appender name="async" class="ch.qos.logback.classic.AsyncAppender">
        <appender-ref ref="accessLog" />
    </appender>

    <logger name="reactor.netty.http.server.AccessLog" level="INFO" additivity="false">
        <appender-ref ref="async"/>
    </logger>
```
11、 cors-configuration(CORS 配置)

12、 actuator-api
```
提供gateway访问的接口

配置添加：
management.endpoint.gateway.enabled=true # default value
management.endpoints.web.exposure.include=gateway

依赖添加：
spring-boot-starter-actuator

1、查看与每条路由关联的谓词和过滤器以及任何可用的配置
/actuator/gateway/routes
2、检索应用于所有路由的全局过滤器
/actuator/gateway/globalfilters
3、检索应用于路由的GatewayFilter工厂
/actuator/gateway/routefilters
3、刷新路由缓存
POST请向发出请求/actuator/gateway/refresh
4、要检索有关单个路由的信息
/actuator/gateway/routes/{id}
5、创建和删除特定路由
```

13、故障排除
```
1、日志级别
org.springframework.cloud.gateway
org.springframework.http.server.reactive
org.springframework.web.reactive
org.springframework.boot.autoconfigure.web
reactor.netty
redisratelimiter

2、窃听
The Reactor Netty HttpClient and HttpServer can have wiretap enabled.
When combined with setting the reactor.netty log level to DEBUG or TRACE,
 it enables the logging of information, 
 such as headers and bodies sent and received across the wire. To enable wiretap, 
 set spring.cloud.gateway.httpserver.wiretap=true or spring.cloud.gateway.httpclient.wiretap=true for the HttpServer and HttpClient,
  respectively.
```