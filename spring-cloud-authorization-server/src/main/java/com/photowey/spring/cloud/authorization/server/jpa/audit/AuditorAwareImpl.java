/*
 * Copyright © 2022 photowey (photowey@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
