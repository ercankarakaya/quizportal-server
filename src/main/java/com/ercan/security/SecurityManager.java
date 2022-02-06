package com.ercan.security;

import com.ercan.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityManager {

    public static User getCurrentUser(){
        if(SecurityContextHolder.getContext() == null || SecurityContextHolder.getContext().getAuthentication() == null){
            return null;
        }
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof User){
            return (User) principal;
        }
        return null;
    }
}
