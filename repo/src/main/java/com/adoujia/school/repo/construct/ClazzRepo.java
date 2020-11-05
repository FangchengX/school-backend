package com.adoujia.school.repo.construct;

import com.adoujia.school.domain.construct.ClazzDO;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author fangcheng
 * @since 2020-11-06 00:01
 */
@Repository
public interface ClazzRepo extends JpaRepository<ClazzDO, Integer> {
    /**
     * find by name and schoolId
     *
     * @param name     name
     * @param schoolId school id
     * @return clazz
     */
    Optional<ClazzDO> findByNameAndSchoolId(String name, Integer schoolId);
}
