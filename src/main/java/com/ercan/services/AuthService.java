package com.ercan.services;

import com.ercan.dtos.requests.LoginRequest;
import com.ercan.dtos.responses.LoginResponse;
import com.ercan.models.User;
import com.ercan.models.UserRole;
import org.springframework.security.core.Authentication;

import java.util.Set;

public interface AuthService {

    LoginResponse signin(LoginRequest request) throws Exception;

    User signup(User user, Set<UserRole> userRoles);

    User getCurrentUser();

    Authentication authenticate(String username, String password) throws Exception;

    boolean isLoggedIn();
}
