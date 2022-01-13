package com.photowey.spring.cloud.authorization.server.jpa.repository;

import com.photowey.spring.cloud.authorization.server.jpa.entity.OAuthUserPrincipal;
import org.springframework.data.repository.CrudRepository;

/**
 * {@code OAuthUserPrincipalRepository}
 *
 * @author photowey
 * @date 2022/01/13
 * @since 1.0.0
 */
public interface OAuthUserPrincipalRepository extends CrudRepository<OAuthUserPrincipal, Long> {

    OAuthUserPrincipal getByUsername(String username);
}
