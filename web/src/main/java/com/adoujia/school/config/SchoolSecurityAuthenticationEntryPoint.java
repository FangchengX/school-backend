package com.adoujia.school.config;

import com.adoujia.school.res.ReqUrl;
import com.adoujia.school.res.ResultVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * Security体系实现
 *
 * @author fangcheng
 */
@Slf4j
@Component
public class SchoolSecurityAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e)
            throws IOException {
        try {
            log.debug("没有通过security的authentication", e);
            res.setCharacterEncoding("UTF-8");
            res.setContentType("application/json");
            String origin = req.getHeader("origin");
            if ((CorsConfig.allowCorsOriginList.contains(origin))) {
                res.setHeader("Access-Control-Allow-Origin", origin.replace("\r\n", "").trim());
            }
            res.setHeader("Access-Control-Allow-Credentials", "true");

            res.getWriter().println(objectMapper.writeValueAsString(
                    ResultVO.error(403, "用户验证失败", new ReqUrl(req.getRequestURL().toString()))));
            res.setStatus(401);
            res.getWriter().flush();
        } catch (Exception exception) {
            log.error("MyAuthenticationEntryPoint出错", exception);
            throw exception;
        }
    }
}

