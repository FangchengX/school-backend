package com.adoujia.school.config;

import com.adoujia.school.res.ReqUrl;
import com.adoujia.school.res.ResultVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

/**
 * @author fangcheng
 */
@Component
public class SchoolSecurityAccessDenied implements AccessDeniedHandler {

    @Autowired
    ObjectMapper objectMapper;

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res, AccessDeniedException e)
            throws IOException {
        res.setCharacterEncoding("UTF-8");
        res.setContentType("application/json");
        res.getWriter().println(objectMapper.writeValueAsString(
                ResultVO.error(401, "用户无访问权限", new ReqUrl(req.getRequestURL().toString()))));
        res.setStatus(401);
        res.getWriter().flush();
    }
}
