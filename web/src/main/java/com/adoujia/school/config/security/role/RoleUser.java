package com.adoujia.school.config.security.role;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * 角色 用户.
 *
 * @author fangcheng
 */


@Target({ElementType.METHOD, ElementType.TYPE})
@PreAuthorize("hasRole('ROLE_USER')")
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RoleUser {
}
