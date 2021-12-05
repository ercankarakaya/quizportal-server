package com.ercan.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Ercan Karakaya 04.12.2021
 */
@Configuration
@EnableJpaRepositories("com.ercan.repository")
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class DatabaseConfig {

    /**
     * @return current auditor
     */
    @Bean
    public AuditorAware<String> auditorAware() {
        return new SecurityAuditorAware();
    }

}
