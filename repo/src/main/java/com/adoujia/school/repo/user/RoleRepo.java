package com.adoujia.school.repo.user;

import com.adoujia.school.domain.user.RoleDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author fangcheng
 * @since 2020-10-25 20:43
 */
@Repository
public interface RoleRepo extends JpaRepository<RoleDO, Integer> {
}
