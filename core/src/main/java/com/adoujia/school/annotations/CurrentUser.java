package com.adoujia.school.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

/**
 * 为了和Spring Security的AuthenticationPrincipal解耦
 * https://docs.spring.io/spring-security/site/docs/current/reference/html5/#mvc-authentication
 * -principal
 *
 * @author fangcheng
 */
@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal(errorOnInvalidType = true)
public @interface CurrentUser {
}

