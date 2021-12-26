package com.ercan.configurations;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;
import java.util.Optional;

/**
 * @author Ercan Karakaya 05.12.2021
 */
@Component
public class SecurityAuditorAware implements AuditorAware<String> {

    /**
     * @return get current user
     */
    @Override
    public Optional<String> getCurrentAuditor() {
//        return Optional.of(SecurityContextHolder
//                .getContext()
//                .getAuthentication()
//                .getName());

//        return Optional.of(Optional.ofNullable(SecurityContextHolder.getContext())
//                .map(SecurityContext::getAuthentication)
//                .filter(Authentication::isAuthenticated)
//                .map(Authentication::getPrincipal)
//                .map(User.class::cast).get().getUsername());
        return Optional.of("Ercan");
    }

}
