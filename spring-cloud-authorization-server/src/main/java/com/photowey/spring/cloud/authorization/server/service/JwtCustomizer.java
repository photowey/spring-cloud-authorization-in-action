package com.photowey.spring.cloud.authorization.server.service;

import org.springframework.security.oauth2.server.authorization.JwtEncodingContext;

/**
 * {@code JwtCustomizer}
 *
 * @author photowey
 * @date 2022/01/13
 * @since 1.0.0
 */
public interface JwtCustomizer {

    void customizeToken(JwtEncodingContext context);

}
