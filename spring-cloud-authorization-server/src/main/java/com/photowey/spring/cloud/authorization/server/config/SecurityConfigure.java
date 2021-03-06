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
package com.photowey.spring.cloud.authorization.server.config;

import com.photowey.spring.cloud.authorization.server.service.OAuthUserPrincipalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * {@code SecurityConfigure}
 *
 * @author photowey
 * @date 2022/01/13
 * @since 1.0.0
 */
@Slf4j
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, order = 0, mode = AdviceMode.PROXY, proxyTargetClass = false)
public class SecurityConfigure {

    @Autowired
    private OAuthUserPrincipalService userPrincipalService;

//    @Bean
//    public static PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(this.userPrincipalService)
                //.passwordEncoder(passwordEncoder())
                .and()
                .eraseCredentials(true);
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests -> authorizeRequests.requestMatchers(EndpointRequest.toAnyEndpoint(), PathRequest.toH2Console()).permitAll()
                .anyRequest().authenticated())
                .formLogin(withDefaults())
                .csrf().ignoringRequestMatchers(PathRequest.toH2Console())
                .and().headers().frameOptions().sameOrigin();

        return http.build();
    }

}
