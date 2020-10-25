package com.adoujia.school.config.security;

import com.adoujia.school.domain.user.UserDO;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

/**
 * 鉴权出错异常
 *
 * @author fangcheng
 */
@Getter
@Setter
@EqualsAndHashCode
public class SchoolSecurityAuthentication extends AbstractAuthenticationToken {

    private List<GrantedAuthority> grantedRoles;
    private boolean isAnonymous;
    private UserDO userDO;

    /**
     * 匿名
     */
    public SchoolSecurityAuthentication() {
        super(Lists.newArrayList());
        setAnonymous(true);
    }

    public SchoolSecurityAuthentication(UserDO userDO) {
        super(Lists.newArrayList());
        this.userDO = userDO;
    }

    public SchoolSecurityAuthentication(Collection<? extends GrantedAuthority> authorities,
                                        UserDO userDO) {
        super(authorities);
        this.grantedRoles = Lists.newArrayList(authorities);
        this.userDO = userDO;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userDO;
    }
}
