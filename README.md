springboot + nacos + sentinel + gateway + openFeign + Security + Sleuth

服务注册与发现:
限流降级：
网关:

service-a/service-b:
1、整合nacos 服务注册与发现 已完成
    1.1 健康监测添加 未完成
2、整合nacos 配置中心 已完成

3、整合sentinel
https://github.com/alibaba/spring-cloud-alibaba/wiki/Sentinel
    3.1 动态数据源支持,push模式  
        https://github.com/alibaba/Sentinel/wiki/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%99%E6%89%A9%E5%B1%95
    3.2 Sentinel 注解支持
    3.3 而在 Web 层直接使用 Spring Cloud Alibaba 自带的 Web 埋点适配