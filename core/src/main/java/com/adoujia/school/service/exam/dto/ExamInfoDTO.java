package com.adoujia.school.service.exam.dto;

import lombok.Data;

/**
 * @author fangcheng
 * @since 2020-11-06 00:33
 */
@Data
public class ExamInfoDTO {
    Integer schoolId;
    Integer examId;
    /**
     * null able
     */
    Integer subjectId;
}
