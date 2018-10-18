package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author:jesse
 * @Description:
 * @Date:Create in 16:56 2018/10/16
 * @Modified By:
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy   //开网关服务
public class LeyouGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeyouGatewayApplication.class,args);
    }
}
