package com.adoujia.school.service.user;

import com.adoujia.school.domain.user.UserDO;
import com.adoujia.school.service.BaseService;
import com.adoujia.school.service.user.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author fangcheng
 * @since 2020-10-26 23:21
 */
@Service
public class UserService extends BaseService {

    /**
     * 用户详情
     *
     * @param userDO user
     * @return user info
     */
    public UserDTO detail(UserDO userDO) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userDO, userDTO);
        return userDTO;
    }
}
