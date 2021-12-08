文档：https://sentinelguard.io/zh-cn/docs/quick-start.html

踩坑：
问题一：Sentinel dashboard 部署在内网中
    1. Sentinel dashboard 会定时获取服务的统计数据
    2. Sentinel dashboard 页面提交设置时需要同步到服务内存中
解决方案: 服务与Sentinel dashboard 部署在同一内网中

问题二：spring boot/spring cloud 整合Sentinel 时的spring boot 版本为 2.3.2.RELEASE
    参考开源框架适配：https://github.com/alibaba/spring-cloud-alibaba/wiki/Sentinel

