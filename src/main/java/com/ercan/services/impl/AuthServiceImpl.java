package com.ercan.services.impl;

import com.ercan.aspects.LoggingAspect;
import com.ercan.dtos.requests.LoginRequest;
import com.ercan.dtos.responses.LoginResponse;
import com.ercan.exceptions.UserAlreadyExistException;
import com.ercan.exceptions.UserNotFoundException;
import com.ercan.models.Role;
import com.ercan.models.User;
import com.ercan.models.UserRole;
import com.ercan.repositories.UserRepository;
import com.ercan.security.jwt.JwtUtil;
import com.ercan.services.AuthService;
import com.ercan.services.RoleService;
import com.ercan.utils.SecurityUtils;
import com.ercan.utils.constans.GlobalContants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public LoginResponse signin(LoginRequest loginRequest) throws Exception {
        try {
            Authentication authentication = authenticate(loginRequest.getUsername(), loginRequest.getPassword());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = this.jwtUtil.generateToken(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            if (isLoggedIn()) logger.info("LOGIN SUCCESS");

            return LoginResponse.builder()
                    .token(token)
                    .authType("Bearer")
                    .username(loginRequest.getUsername())
                    .tokenExpiryTime(GlobalContants.EXPIRATION_MILLIS)
                    .build();

        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public User signup(User user, Set<UserRole> userRoles) {
        if (userRepository.existsUsersByUsername(user.getUsername())) {
            throw new UserAlreadyExistException("Username already present!");
        } else if (userRepository.existsUserByEmail(user.getEmail())) {
            throw new UserAlreadyExistException("Email already present!");
        } else {
            userRoles.forEach(userRole -> {
                Role role = roleService.save(userRole.getRole());
                userRole.setRole(role);
            });
        }
        user.setUserRoles(userRoles); //user.getUserRoles().addAll(userRoles);
        user = userRepository.save(user);
        return user;
    }

    @Override
    public User getCurrentUser() {
        return SecurityUtils.getCurrentUser();
    }

    @Override
    public Authentication authenticate(String username, String password) throws Exception {
        try {
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("User Disabled " + e.getMessage());
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Credentials " + e.getMessage());
        }
    }

    @Override
    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }


}
