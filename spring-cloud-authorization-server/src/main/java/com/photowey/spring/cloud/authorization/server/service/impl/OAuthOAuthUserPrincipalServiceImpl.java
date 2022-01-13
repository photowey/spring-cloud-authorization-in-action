package com.photowey.spring.cloud.authorization.server.service.impl;

import com.photowey.spring.cloud.authorization.server.jpa.entity.OAuthUserPrincipal;
import com.photowey.spring.cloud.authorization.server.jpa.repository.OAuthUserPrincipalRepository;
import com.photowey.spring.cloud.authorization.server.service.OAuthUserPrincipalService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@code OAuthUserPrincipalServiceImpl}
 *
 * @author photowey
 * @date 2022/01/13
 * @since 1.0.0
 */
@Service
public class OAuthOAuthUserPrincipalServiceImpl implements OAuthUserPrincipalService {

    private final OAuthUserPrincipalRepository userPrincipalRepository;

    public OAuthOAuthUserPrincipalServiceImpl(OAuthUserPrincipalRepository userPrincipalRepository) {
        this.userPrincipalRepository = userPrincipalRepository;
    }

    @Override
    @Transactional
    public OAuthUserPrincipal loadUserByUsername(String username) {

        OAuthUserPrincipal principal = this.userPrincipalRepository.getByUsername(username);
        principal.getAuthorities().size();
        principal.getPassword();

        return principal;
    }

}
