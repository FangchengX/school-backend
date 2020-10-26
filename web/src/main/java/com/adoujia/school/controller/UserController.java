package com.adoujia.school.controller;

import com.adoujia.school.annotations.CurrentUser;
import com.adoujia.school.config.security.role.RoleUser;
import com.adoujia.school.domain.user.UserDO;
import com.adoujia.school.res.ResultVO;
import com.adoujia.school.service.user.UserService;
import com.adoujia.school.service.user.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fangcheng
 * @since 2020-10-26 23:31
 */
@Api(tags = "用户相关")
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @ApiOperation("用户详情")
    @GetMapping("detail")
    @RoleUser
    public ResultVO<UserDTO> detail(
        @CurrentUser UserDO userDO
    ) {
        return ResultVO.ok(userService.detail(userDO));
    }
}
