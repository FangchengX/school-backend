package com.adoujia.school.controller;

import com.adoujia.school.config.security.role.RoleUser;
import com.adoujia.school.res.ResultVO;
import com.adoujia.school.service.user.UserLoginService;
import com.adoujia.school.service.user.dto.LoginDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fangcheng
 * @since 2020-10-26 23:26
 */
@Api(tags = "登陆相关")
@RestController
@RequestMapping("sign")
public class LoginController {
    @Autowired
    UserLoginService loginService;

    @ApiOperation("登陆")
    @PostMapping("in")
    public ResultVO<String> login(
        @RequestBody LoginDTO loginDTO
    ) {
        return ResultVO.ok(loginService.login(loginDTO));
    }

    @ApiOperation("登出")
    @PostMapping("out")
    @RoleUser
    public ResultVO<Void> signOut(
        HttpServletRequest request
    ) {
        loginService.signOut(request);
        return ResultVO.ok();
    }
}
