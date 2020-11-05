package com.adoujia.school.repo.construct;

import com.adoujia.school.domain.construct.StudentDO;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author fangcheng
 * @since 2020-11-06 00:02
 */
@Repository
public interface StudentRepo extends JpaRepository<StudentDO, Integer> {
    /**
     * find by clazz id and code
     *
     * @param clazzId clazz id
     * @param code    code
     * @return student
     */
    Optional<StudentDO> findByClassIdAndCode(Integer clazzId, String code);
}
