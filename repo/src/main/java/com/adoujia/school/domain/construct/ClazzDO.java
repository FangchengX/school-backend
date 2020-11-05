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
 * @since 2020-11-05 23:50
 */
@Data
@Entity
@Table(name = "`class`")
public class ClazzDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    Integer schoolId;
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
