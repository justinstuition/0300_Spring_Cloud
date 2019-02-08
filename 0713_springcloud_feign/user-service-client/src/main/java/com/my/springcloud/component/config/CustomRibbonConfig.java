package com.my.springcloud.component.config;

import com.my.springcloud.component.MyPing;
import com.my.springcloud.component.MyRule;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Instuition
 * @date 2019/2/1 21:33
 * @since 0.0.1
 */
@Configuration
public class CustomRibbonConfig {

    /**
     * 将 {@link MyRule} 暴露成 {@link Bean}
     *
     * @return
     */
    @Bean
    public IRule ribbonRule() {
        return new MyRule();
    }

    /**
     * 将 {@link MyPing} 暴露成 {@link Bean}
     *
     * @return
     */
    //@Bean 改为在 bootstrap.properties 中配置类实现
    public IPing ribbonPing() {
        return new MyPing();
    }
}
