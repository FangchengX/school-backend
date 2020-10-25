package com.adoujia.school.config;

import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 配置全局跨域
 *
 * @author fangcheng
 */
@Configuration
public class CorsConfig {

    static List<String> allowCorsOriginList = Lists.newArrayList(
            "http://localhost:9527",
            "http://localhost:8081",
            "http://127.0.0.1:8081",
            "http://localhost:8000",
            "http://127.0.0.1:8080",
            "https://hhms.mrboxfood.com",
            "https://servicewechat.com"
    );

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", getConfig());
        return new CorsFilter(source);
    }

    private CorsConfiguration getConfig() {
        CorsConfiguration config = new CorsConfiguration();
        for (String origin : allowCorsOriginList) {
            config.addAllowedOrigin(origin);
        }
        config.addAllowedOrigin("*");
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        config.setAllowCredentials(true);
        return config;
    }

}
