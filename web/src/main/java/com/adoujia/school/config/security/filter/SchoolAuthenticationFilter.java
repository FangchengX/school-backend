package com.adoujia.school.config.security.filter;

import com.adoujia.school.config.SchoolSecurityAuthenticationEntryPoint;
import com.adoujia.school.config.security.SchoolSecurityAuthentication;
import com.adoujia.school.domain.user.UserDO;
import com.adoujia.school.service.user.UserLoginService;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author fangcheng
 * 基于Token的登录
 */
@Component
public class SchoolAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    UserLoginService userLoginService;
    @Autowired
    SchoolSecurityAuthenticationEntryPoint entryPoint;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        try {
            UserDO userDO = userLoginService.getUserFromRequest(request);
            Authentication authentication = Objects.isNull(userDO) ?
                    authenticateAsAnonymousUser() : authenticateUser(userDO);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (BadCredentialsException e) {
            entryPoint.commence(request, response, e);
            return;
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 作为匿名用户登录
     *
     * @return 匿名用户的登录信息
     */
    private SchoolSecurityAuthentication authenticateAsAnonymousUser() {
        SchoolSecurityAuthentication authentication = new SchoolSecurityAuthentication();
        authentication.setAnonymous(true);
        authentication.setAuthenticated(true);
        return authentication;
    }

    /**
     * 注入用户
     *
     * @param userDO user
     * @return authentication
     */
    private SchoolSecurityAuthentication authenticateUser(
            UserDO userDO) {
        return new SchoolSecurityAuthentication(userDO);
    }
}
