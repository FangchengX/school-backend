package com.adoujia.school.domain.construct;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * @author fangcheng
 * @since 2020-11-05 23:51
 */
@Data
@Entity
@Table(name = "teacher")
public class TeacherDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer schoolId;
    Integer subjectId;
    String name;
    Integer userId;
    Integer gender;
    Integer del;
    @CreatedDate
    LocalDateTime createTime;
    @LastModifiedDate
    LocalDateTime updateTime;

    @PrePersist
    public void init() {
        if (del == null) {
            del = 0;
        }
    }
}
