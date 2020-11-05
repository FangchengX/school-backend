package com.adoujia.school.service.construct;

import com.adoujia.school.domain.construct.StudentDO;
import com.adoujia.school.service.BaseService;
import com.adoujia.school.util.GenderUtil;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author fangcheng
 * @since 2020-11-06 00:29
 */
@Service
@Slf4j
public class StudentService extends BaseService {
    public StudentDO findStudent(Integer clazzId, String code, String studentName, String gender) {
        Optional<StudentDO> optional = studentRepo.findByClassIdAndCode(clazzId, code);
        if (optional.isPresent()) {
            return optional.get();
        }
        StudentDO studentDO = new StudentDO();
        studentDO.setClassId(clazzId);
        studentDO.setCode(code);
        studentDO.setGender(GenderUtil.getGender(gender));
        studentDO.setName(studentName);
        return studentRepo.save(studentDO);
    }
}
