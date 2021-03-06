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
package com.photowey.spring.cloud.authorization.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@code OAuth2ClientConfigure}
 *
 * @author photowey
 * @date 2022/01/13
 * @since 1.0.0
 */
@Configuration
public class OAuth2ClientConfigure {

    private static List<String> clients = Arrays.asList("client_credentials", "authorization_code", "password");

    @Value("${oauth2.authorization.uri}")
    private String oauth2AuthorizationUri;

    @Value("${oauth2.token.uri}")
    private String oauth2TokenUri;

    @Value("${local.config.auth.server.host}")
    private String host;
    @Value("${local.config.auth.server.port}")
    private Integer port;
    @Value("${local.config.auth.server.path}")
    private String path;

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {

        List<ClientRegistration> registrations = clients.stream()
                .map(c -> getRegistration(c))
                .filter(registration -> registration != null)
                .collect(Collectors.toList());

        return new InMemoryClientRegistrationRepository(registrations);
    }

    private ClientRegistration getRegistration(String client) {

        if (client.equals("client_credentials")) {
            ClientRegistration clientCredentialsClientRegistration = clientCredentialsClientRegistration();
            return clientCredentialsClientRegistration;
        }

        if (client.equals("authorization_code")) {
            ClientRegistration authorizationCodeClientRegistration = authorizationCodeClientRegistration();
            return authorizationCodeClientRegistration;
        }

        if (client.equals("password")) {
            ClientRegistration passwordClientRegistration = passwordClientRegistration();
            return passwordClientRegistration;
        }

        return null;
    }

    private ClientRegistration oidcClientRegistration() {
        return ClientRegistration.withRegistrationId("messaging-client-oidc")
                .clientId("messaging-client")
                .clientSecret("secret")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                .scope("openid")
                .authorizationUri(String.format("http://%s:%s/%s/connect/register", host, port, path))
                .tokenUri(oauth2TokenUri)
                .clientName("messaging-client-oidc")
                .build();
    }

    private ClientRegistration clientCredentialsClientRegistration() {
        return ClientRegistration.withRegistrationId("messaging-client-client-credentials")
                .clientId("client-credentials-messaging-client")
                .clientSecret("secret1")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .scope("message.read", "message.write")
                .authorizationUri(oauth2AuthorizationUri)
                .tokenUri(oauth2TokenUri)
                .build();
    }

    private ClientRegistration authorizationCodeClientRegistration() {
        return ClientRegistration.withRegistrationId("messaging-client-authorization-code")
                .clientId("authorization-code-messaging-client")
                .clientSecret("secret2")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .redirectUri("{baseUrl}/authorized")
                .scope("message.read", "message.write")
                .authorizationUri(oauth2AuthorizationUri)
                .tokenUri(oauth2TokenUri)
                .build();
    }

    private ClientRegistration passwordClientRegistration() {

        return ClientRegistration.withRegistrationId("messaging-client-password")
                .clientId("password-messaging-client")
                .clientSecret("secret3")
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .authorizationGrantType(AuthorizationGrantType.PASSWORD)
                //.scope("message.read", "message.write")
                .authorizationUri(oauth2AuthorizationUri)
                .tokenUri(oauth2TokenUri)
                .build();
    }

}
