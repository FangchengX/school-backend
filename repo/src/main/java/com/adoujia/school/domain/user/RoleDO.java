package com.adoujia.school.domain.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * @author fangcheng
 * @since 2020-10-25 15:25
 */
@Data
@Entity
@Table(name = "role")
public class RoleDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    String role;
}
