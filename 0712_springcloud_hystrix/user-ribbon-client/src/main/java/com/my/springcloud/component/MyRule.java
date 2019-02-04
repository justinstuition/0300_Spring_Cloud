package com.my.springcloud.component;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.Optional;

/**
 * 自定义 {@link IRule} 实现，永远选择第一台可达服务器
 *
 * @author Instuition
 * @date 2019/2/1 21:19
 * @since 0.0.1
 */
public class MyRule extends AbstractLoadBalancerRule {
    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {

    }

    @Override
    public Server choose(Object key) {
        ILoadBalancer loadBalancer = this.getLoadBalancer();
        Optional<Server> firstOptServer = loadBalancer.getReachableServers()
                                                      .stream()
                                                      .findFirst();
        if (!firstOptServer.isPresent()) {
            return null;
        }
        return firstOptServer.get();
    }
}
