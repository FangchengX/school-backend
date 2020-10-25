package com.adoujia.school.repo.user;

import com.adoujia.school.domain.user.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author fangcheng
 * @since 2020-10-25 20:42
 */
@Repository
public interface UserRepo extends JpaRepository<UserDO, Integer> {
}
