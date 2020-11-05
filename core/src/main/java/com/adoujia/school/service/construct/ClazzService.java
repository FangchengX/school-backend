package com.adoujia.school.service.construct;

import com.adoujia.school.domain.construct.ClazzDO;
import com.adoujia.school.service.BaseService;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author fangcheng
 * @since 2020-11-06 00:19
 */
@Service
@Slf4j
public class ClazzService extends BaseService {
    /**
     * find class by name and school id
     * if not exist create one
     *
     * @param clazzName class name
     * @param schoolId  school id
     * @return class
     */
    public ClazzDO findClazz(String clazzName, Integer schoolId) {
        Optional<ClazzDO> optional = clazzRepo.findByNameAndSchoolId(clazzName, schoolId);
        if (optional.isPresent()) {
            ClazzDO clazzDO = optional.get();
            if (Objects.equals(clazzDO.getDel(), 1)) {
                clazzDO.setDel(0);
                clazzDO = clazzRepo.save(clazzDO);
            }
            return clazzDO;
        }
        ClazzDO clazzDO = new ClazzDO();
        clazzDO.setName(clazzName);
        clazzDO.setSchoolId(schoolId);
        log.info("新增班级, {}, {}", schoolId, clazzName);
        return clazzRepo.save(clazzDO);
    }
}
