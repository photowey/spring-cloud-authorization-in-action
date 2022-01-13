package com.photowey.spring.cloud.authorization.server.config;

import com.photowey.spring.cloud.authorization.server.jpa.audit.AuditorAwareImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * {@code JpaAuditingConfigure}
 *
 * @author photowey
 * @date 2022/01/13
 * @since 1.0.0
 */
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class JpaAuditingConfigure {

    @Bean
    public AuditorAware<Long> auditorAware() {
        return new AuditorAwareImpl();
    }

}
