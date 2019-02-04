package com.my.springcloud.component;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.Server;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 实现 {@link IPing} 接口，检查服务 /actuator/health 是否正常状态码：200
 *
 * @author Instuition
 * @date 2019/2/1 22:11
 * @since 0.0.1
 */
public class MyPing implements IPing {

    /**
     * 异步线程定期去 Ping 服务端
     *
     * @param server
     * @return
     */
    @Override
    public boolean isAlive(Server server) {
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
        uriComponentsBuilder.scheme(server.getScheme());
        uriComponentsBuilder.host(server.getHost());
        uriComponentsBuilder.port(server.getPort());
        uriComponentsBuilder.path("/actuator/health");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uriComponentsBuilder.build().toUri(), String.class);

        return HttpStatus.OK.equals(responseEntity.getStatusCode());
    }
}
