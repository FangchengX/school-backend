package com.adoujia.school.config;

import com.adoujia.school.config.security.GrantedRole;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

/**
 * 添加@Order注解，
 * 没有Order注解，越前生效
 * Order数字越大，越前生效
 *
 * @author fangcheng
 */
@Configuration
@Order(1)
public class SwaggerSecurity extends WebSecurityConfigurerAdapter {

    static final String[] antPatterns = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "favicon.icon"
    };

    static final List<RequestMatcher> matchers =
            Stream.of(antPatterns).map(AntPathRequestMatcher::new).collect(Collectors.toList());

    @Autowired
    SwaggerAuthenticationProvider swaggerAuthenticationProvider;

    public static boolean isSwaggerUrl(HttpServletRequest request) {
        return matchers.stream().anyMatch(m -> m.matches(request));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .requestMatchers()
                .antMatchers(antPatterns)
                .and()
                .authorizeRequests()
                .anyRequest()
                .hasRole("SWAGGER")
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(swaggerAuthenticationProvider);
    }

    @Component
    public static class SwaggerAuthenticationProvider implements AuthenticationProvider {

        @Override
        public Authentication authenticate(Authentication authentication) {
            UsernamePasswordAuthenticationToken token =
                    ((UsernamePasswordAuthenticationToken) authentication);
            if (checkSwaggerPassword(token)) {
                return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
                        authentication.getCredentials(),
                        Lists.newArrayList(new GrantedRole("ROLE_SWAGGER")));
            }
            return authentication;
        }

        private boolean checkSwaggerPassword(UsernamePasswordAuthenticationToken token) {
            if (token.getPrincipal() == null || token.getCredentials() == null) {
                return false;
            }
            return "swagger".equals(token.getPrincipal().toString())
                    && "todayisagoodday".equals(token.getCredentials().toString());
        }

        @Override
        public boolean supports(Class<?> authentication) {
            return UsernamePasswordAuthenticationToken.class == authentication;
        }


    }
}

