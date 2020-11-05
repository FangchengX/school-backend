package com.adoujia.school.service.exam;

import com.adoujia.school.config.InnerRuntimeException;
import com.adoujia.school.domain.construct.ClazzDO;
import com.adoujia.school.domain.construct.StudentDO;
import com.adoujia.school.service.construct.ClazzService;
import com.adoujia.school.service.construct.StudentService;
import com.adoujia.school.service.exam.dto.ExamInfoDTO;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author fangcheng
 * @since 2020-11-05 23:40
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class GradeImportService {
    @Autowired
    ClazzService clazzService;
    @Autowired
    StudentService studentService;

    /**
     * import grade
     *
     * @param inputStream excel input stream
     */
    public void importGrade(InputStream inputStream, ExamInfoDTO examInfoDTO) {
        try (Workbook workbook = new XSSFWorkbook(inputStream)) {
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                importGradeBySheet(workbook.getSheetAt(i), examInfoDTO);
            }
        } catch (Exception e) {
            throw new InnerRuntimeException("导入成绩失败");
        }
    }

    /**
     * import grade by sheet
     * 序号，编号，班级，姓名，性别，成绩
     *
     * @param sheet       工作表
     * @param examInfoDTO 考试信息
     */
    private void importGradeBySheet(Sheet sheet, ExamInfoDTO examInfoDTO) {
        Map<String, ClazzDO> clazzMap = new HashMap<>(4);
        for (int i = 1; i < sheet.getLastRowNum(); i++) {
            try {
                importGradeByRow(sheet.getRow(i), clazzMap, examInfoDTO);
            } catch (Exception e) {
                //do nothing
            }
        }
    }

    /**
     * import grade by row
     * 序号，编号，班级，姓名，性别，成绩
     *
     * @param row         row
     * @param clazzMap    clazzMap
     * @param examInfoDTO exam info
     */
    private void importGradeByRow(Row row, Map<String, ClazzDO> clazzMap, ExamInfoDTO examInfoDTO) {
        String clazzName = row.getCell(2).getStringCellValue();
        ClazzDO clazzDO = clazzMap.computeIfAbsent(clazzName, unused -> clazzService.findClazz(clazzName,
            examInfoDTO.getSchoolId()));
        String code = row.getCell(1).getStringCellValue();
        String studentName = row.getCell(3).getStringCellValue();
        String gender = row.getCell(4).getStringCellValue();
        StudentDO studentDO = studentService.findStudent(clazzDO.getId(), code, studentName, gender);
        //TODO save grade
    }
}
