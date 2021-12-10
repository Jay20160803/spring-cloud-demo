package com.example.servicea.config.sentinel;

import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.nacos.NacosDataSource;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Andy
 * Created time on 2021/12/8 17:27
 * @description sentinel nacos 动态数据源 启动时配置
 */
@Slf4j
@Component
@RefreshScope
public class SentinelNacosDataSourceConfig implements ApplicationRunner {

    @Value("${spring.cloud.nacos.discovery.server-addr}")
    private String nacosAddress;

    @Value("${spring.cloud.sentinel.flow-rule.groupId}")
    private String groupId;

    @Value("${spring.cloud.sentinel.flow-rule.dataId}")
    private String flowRuleDataId;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        try {
            loadFlowRules();
        } catch (Exception e) {
            log.error("sentinel nacos data 加载异常",e);
        }

    }

    //加载流量控制规则
    private void loadFlowRules() {
        ReadableDataSource<String, List<FlowRule>> flowRuleDataSource = new NacosDataSource<>(nacosAddress, groupId, flowRuleDataId,
                source -> JSON.parseObject(source, new TypeReference<List<FlowRule>>() {
                }));
        FlowRuleManager.register2Property(flowRuleDataSource.getProperty());
    }
}
