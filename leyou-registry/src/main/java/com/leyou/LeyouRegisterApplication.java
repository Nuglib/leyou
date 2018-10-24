package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author:jesse
 * @Description:
 * @Date:Create in 16:37 2018/10/16
 * @Modified By:
 */
@SpringBootApplication
@EnableEurekaServer  //开启Eureka服务端
public class LeyouRegisterApplication {
    public static void main(String[] args) {
        SpringApplication.run(LeyouRegisterApplication.class,args);
    }
}
