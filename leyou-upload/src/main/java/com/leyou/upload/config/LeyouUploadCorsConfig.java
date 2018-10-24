package com.leyou.upload.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Author:jesse
 * @Description:
 * @Date:Create in 9:36 2018/10/23
 * @Modified By:
 */
@Configuration
public class LeyouUploadCorsConfig {
    @Bean
    public CorsFilter corsFilter(){

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://manage.leyou.com");
        corsConfiguration.addAllowedMethod("POST");
        corsConfiguration.addAllowedMethod("OPTIONS");
        corsConfiguration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource urlConfigSource = new UrlBasedCorsConfigurationSource();
        urlConfigSource.registerCorsConfiguration("/**",corsConfiguration);
        return new CorsFilter(urlConfigSource);
    }
}
