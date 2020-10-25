package com.adoujia.school.config.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * Security体系实现
 *
 * @author fangcheng
 */
@Data
public class GrantedRole implements GrantedAuthority {

    private String role;

    public GrantedRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return this.role;
    }
}
