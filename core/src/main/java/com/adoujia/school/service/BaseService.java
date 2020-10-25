package com.adoujia.school.service;

import com.adoujia.school.repo.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author fangcheng
 * @since 2020-10-25 22:24
 */
public class BaseService {
    @Autowired
    protected UserRepo userRepo;
    @Autowired
    protected RedisService redisService;
}
