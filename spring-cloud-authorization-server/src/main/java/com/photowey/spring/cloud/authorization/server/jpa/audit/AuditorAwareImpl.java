package com.photowey.spring.cloud.authorization.server.jpa.audit;

import com.photowey.spring.cloud.authorization.server.jpa.entity.OAuthUserPrincipal;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

/**
 * {@code AuditorAwareImpl}
 *
 * @author photowey
 * @date 2022/01/13
 * @since 1.0.0
 */
public class AuditorAwareImpl implements AuditorAware<Long> {

    private static boolean isPrincipalAuthenticated(Authentication principal) {
        return principal != null &&
                !AnonymousAuthenticationToken.class.isAssignableFrom(principal.getClass()) && principal.isAuthenticated();
    }

    @Override
    public Optional<Long> getCurrentAuditor() {

        Long userId = 0L;

        Authentication principal = SecurityContextHolder.getContext().getAuthentication();
        if (isPrincipalAuthenticated(principal)) {
            OAuthUserPrincipal userPrincipal = (OAuthUserPrincipal) principal.getPrincipal();
            userId = userPrincipal.getId();
        }

        return Optional.of(userId);
    }

}
