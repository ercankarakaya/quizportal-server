package com.ercan.configurations;

import com.ercan.models.User;
import com.ercan.utils.SecurityUtils;
import com.ercan.utils.constans.GlobalContants;
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

        return Optional.of(
                Optional.ofNullable(SecurityUtils.getCurrentUser())
                        .map(User::getUsername)
                        .orElse(GlobalContants.SYSTEM_ACCOUNT));

        /*
        return Optional.of(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName());
         */

   /*
        return Optional.of(Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getPrincipal)
                .map(item -> {
                    if (item instanceof UserDetails)
                        return ((User) item).getUsername();
                    else
                        return ((String) item);//null
                })).get();
   */

    }

}
