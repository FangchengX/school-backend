package com.adoujia.school.config;

import com.adoujia.school.config.security.SchoolAuthenticationProvider;
import com.adoujia.school.config.security.filter.SchoolAuthenticationFilter;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * WebSecurity配置
 * 没有@Order注解，默认是最后一个安全配置
 *
 * @author fangcheng
 */
@Slf4j
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    SchoolSecurityAuthenticationEntryPoint myAuthenticationEntryPoint;
    @Autowired
    SchoolSecurityAccessDenied myAccessDenied;
    @Autowired
    SchoolAuthenticationFilter schoolAuthenticationFilter;
    @Autowired
    SchoolAuthenticationProvider schoolAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        try {

            http.headers()
                    .frameOptions().disable()
                    .and()
                    //允许OPTIONS请求
                    .authorizeRequests()
                    .antMatchers(HttpMethod.OPTIONS)
                    .permitAll()
                    .and()

                    //所有请求进行鉴权
                    .authorizeRequests()
                    .anyRequest()
                    .authenticated()
                    //使用@Role注解完成授权
                    .accessDecisionManager(new UnanimousBased(List.of(new WebExpressionVoter())))
                    .and()

                    //每次请求单独鉴权，关闭csrf和session
                    .csrf().disable()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    //鉴权所需的Filter
                    .addFilterBefore(schoolAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class)
                    //让请求body可以读两次
                    //读取body进行摘要校验
                    //允许跨域，参考CorsConfig
                    .cors()
                    .and()
                    // 用户未验证通过的响应
                    .exceptionHandling()
                    .authenticationEntryPoint(myAuthenticationEntryPoint)
                    // 用户验证通过，但无资源权限
                    .accessDeniedHandler(myAccessDenied);

        } catch (Exception e) {
            log.error("WebSecurityConfig出错", e);
            throw e;
        }
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(schoolAuthenticationProvider);
    }
}
