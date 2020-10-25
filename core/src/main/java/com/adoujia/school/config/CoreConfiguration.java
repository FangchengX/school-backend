package com.adoujia.school.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author fangcheng
 */
@Configuration
//默认使用core.properties，不过可以被覆盖
@PropertySource(ignoreResourceNotFound = true, value = "classpath:core.properties")
@PropertySource(ignoreResourceNotFound = true, value = "classpath:core-${spring.profiles.active}.properties")
public class CoreConfiguration {

}
