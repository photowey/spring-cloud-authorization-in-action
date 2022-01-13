package com.photowey.spring.cloud.authorization.client.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * {@code SecurityConfigure}
 *
 * @author photowey
 * @date 2022/01/13
 * @since 1.0.0
 */
@EnableWebSecurity
public class SecurityConfigure {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.eraseCredentials(false);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login").failureUrl("/login-error").permitAll())
                .oauth2Client(withDefaults());

        return http.build();
    }

    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/webjars/**");
    }

    @Bean
    UserDetailsService users() {

        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("user1")
                .password("password")
                .authorities("USER")
                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }

}
