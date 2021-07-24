package com.training.springbootbuyitem.configuration;

import com.training.springbootbuyitem.component.CurrentUserHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Slf4j
@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfiguration {

    private final CurrentUserHelper currentUserHelper;

    public JpaAuditingConfiguration(CurrentUserHelper currentUserHelper) {
        this.currentUserHelper = currentUserHelper;
    }

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.ofNullable(currentUserHelper.getEmail());
    }

}
