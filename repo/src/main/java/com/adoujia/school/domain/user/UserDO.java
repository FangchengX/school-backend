package com.adoujia.school.domain.user;

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
 * @since 2020-10-25 14:56
 */
@Data
@Entity
@Table(name = "user")
public class UserDO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String account;
    String name;
    String avatar;
    String gender;
    String phone;
    String password;
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
