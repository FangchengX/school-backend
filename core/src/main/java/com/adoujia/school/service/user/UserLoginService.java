package com.adoujia.school.service.user;

import com.adoujia.school.domain.user.UserDO;
import com.adoujia.school.service.BaseService;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

/**
 * @author fangcheng
 * @since 2020-10-25 22:23
 */
@Service
public class UserLoginService extends BaseService {

    public static final String TOKEN_HEADER = "userToken";

    /**
     * get user info from request
     *
     * @param request request
     * @return user
     */
    public UserDO getUserFromRequest(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);
        if (Objects.isNull(token)) {
            return null;
        }
        String value = redisService.get(token);
        if (Objects.isNull(value)) {
            return null;
        }
        Integer userId = Integer.valueOf(value);
        return userRepo.findById(userId).orElse(null);
    }
}
