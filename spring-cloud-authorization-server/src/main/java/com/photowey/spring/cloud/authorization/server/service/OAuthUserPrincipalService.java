package com.photowey.spring.cloud.authorization.server.service;

import com.photowey.spring.cloud.authorization.server.jpa.entity.OAuthUserPrincipal;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;

/**
 * {@code OAuthUserPrincipalService}
 *
 * @author photowey
 * @date 2022/01/13
 * @since 1.0.0
 */
@Validated
public interface OAuthUserPrincipalService extends UserDetailsService {

    @Override
    OAuthUserPrincipal loadUserByUsername(String username);

}
