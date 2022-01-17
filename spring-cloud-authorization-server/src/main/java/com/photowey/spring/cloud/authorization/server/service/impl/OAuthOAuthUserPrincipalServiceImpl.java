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
