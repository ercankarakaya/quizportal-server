package com.ercan.utils;

import com.ercan.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Utility class for Spring Security.
 */
@Component
public class SecurityUtil {

    /**
     * Get the login of the current user.
     */
    public static User getCurrentUser() {
        if (SecurityContextHolder.getContext() == null || SecurityContextHolder.getContext().getAuthentication() == null) {
            return null;
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof User) {
            return (User) principal;
        }
        return null;
    }

    /**
     * Check if a user is authenticated.
     *
     * @return true if the user is authenticated, false otherwise
     */
    public static boolean isAuthenticated() {
        return Optional.ofNullable(getCurrentUser()).isPresent();
    }

    /**
     * If the current user has a specific security role.
     */
    public static boolean isUserInAnyRole(String... roles) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof User) {
                User principal = (User) authentication.getPrincipal();
                return principal.getAuthorities().stream().anyMatch(a ->
                        Stream.of(roles).anyMatch(r -> r.equals(a.getAuthority())));
            }
        }
        return false;
    }

}
