package com.adoujia.school.service.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author fangcheng
 * @since 2020-10-26 23:04
 */
@Data
public class UserDTO {
    String account;
    String name;
    String avatar;
    String phone;
    @ApiModelProperty("0-未知，1-男，2-女")
    Integer gender;
}
