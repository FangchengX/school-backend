package com.adoujia.school.service.user;

import com.adoujia.school.config.InnerRuntimeException;
import com.adoujia.school.domain.user.UserDO;
import com.adoujia.school.service.BaseService;
import com.adoujia.school.service.user.dto.LoginDTO;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
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

    /**
     * sign out
     *
     * @param request request
     */
    public void signOut(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_HEADER);
        redisService.delete(token);
    }

    /**
     * login
     *
     * @param loginDTO login info
     * @return token
     */
    public String login(LoginDTO loginDTO) {
        UserDO userDO = userRepo.findByAccount(loginDTO.getAccount())
            .orElseThrow(() -> new InnerRuntimeException("NOT FOUND"));
        checkPassword(loginDTO.getPassword(), userDO.getPassword());
        String token = UUID.randomUUID().toString().replace("-", "");
        redisService.save(token, userDO.getId() + "", 24, TimeUnit.HOURS);
        return token;
    }

    /**
     * check password
     * TODO 加密
     *
     * @param password     输入的密码
     * @param passwordFind 储存的密码
     */
    private void checkPassword(String password, String passwordFind) {
        if (!Objects.equals(password, passwordFind)) {
            throw new InnerRuntimeException("密码错误");
        }
    }
}
