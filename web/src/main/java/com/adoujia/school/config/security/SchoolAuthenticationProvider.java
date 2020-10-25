package com.adoujia.school.config.security;

import com.adoujia.school.domain.user.UserDO;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/**
 * security框架的实现类， 可以获取用户的权限.
 *
 * @author fangcheng
 */
@Component
@Slf4j
public class SchoolAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) {
        SchoolSecurityAuthentication auth = (SchoolSecurityAuthentication) authentication;
        UserDO user = auth.getUserDO();
        if (Objects.isNull(user)) {
            return auth;
        }
        //TODO find user roles
        List<String> roles = Lists.newArrayList("ROLE_USER");
        List<GrantedRole> myGrantedAuthorities =
                roles.stream().map(GrantedRole::new).collect(Collectors.toList());
        SchoolSecurityAuthentication returnAuth =
                new SchoolSecurityAuthentication(myGrantedAuthorities, user);
        returnAuth.setAuthenticated(true);
        return returnAuth;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }

}
