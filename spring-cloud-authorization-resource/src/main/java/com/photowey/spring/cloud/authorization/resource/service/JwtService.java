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
package com.photowey.spring.cloud.authorization.resource.service;

import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.util.Assert;

/**
 * {@code JwtService}
 *
 * @author photowey
 * @date 2022/01/13
 * @since 1.0.0
 */
public interface JwtService {

    String USER_ID_CLAIM = "userId";
    String EMAIL_CLAIM = "email";
    String PRINCIPAL_USER_TYPE_CLAIM = "principalUserType";

    static <T> T getClaim(Jwt jwt, String claim, Class<T> clazz) {

        Assert.notNull(jwt, "jwt cannot be null");
        Assert.hasText(claim, "claim cannot be null or empty");

        T value = null;

        if (jwt.hasClaim(claim)) {
            value = jwt.getClaim(claim);
        }

        return value;
    }

    static Long getUserId(Jwt jwt) {
        return getClaim(jwt, USER_ID_CLAIM, Long.class);
    }

    static String getEmail(Jwt jwt) {
        return getClaim(jwt, EMAIL_CLAIM, String.class);
    }

    static String getPrincipalUserType(Jwt jwt) {
        return getClaim(jwt, PRINCIPAL_USER_TYPE_CLAIM, String.class);
    }

}
