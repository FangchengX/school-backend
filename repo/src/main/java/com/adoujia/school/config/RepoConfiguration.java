package com.adoujia.school.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author fangcheng
 */
@Configuration
@PropertySource(ignoreResourceNotFound = true, value = "classpath:jdbc.properties")
@PropertySource(ignoreResourceNotFound = true, value = "classpath:jdbc-${spring.profiles.active}.properties")
@EnableTransactionManagement
public class RepoConfiguration {
}
