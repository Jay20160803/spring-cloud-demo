springboot + nacos + sentinel + gateway + openFeign + Security + Sleuth

服务注册与发现:
限流降级：
网关:
# spring boot/spring cloud/spring cloud alibaba 版本关系
```
 spring cloud alibaba 版本对应的 spring boot 版本 一致
 spring cloud 对应的spring boot 版本在 github wiki 中查看
```

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
```
- 