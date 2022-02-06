package com.ercan.controllers;

import com.ercan.exceptions.UserNotFoundException;
import com.ercan.dtos.LoginRequest;
import com.ercan.dtos.LoginResponse;
import com.ercan.security.jwt.JwtUtil;
import com.ercan.services.impl.UserDetailsServiceImpl;
import com.ercan.utils.constans.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Mappings.AUTH)
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    @Autowired
    private JwtUtil jwtUtil;


    @PostMapping(Mappings.GENERATE_TOKEN)
    public ResponseEntity<?> generateToken(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        } catch (UserNotFoundException e) {
            e.printStackTrace();
            throw new Exception("User not found ");
        }

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(loginRequest.getUsername());
        String token = this.jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(LoginResponse.builder()
                .token(token)
                .username(loginRequest.getUsername())
                .authType("Bearer")
                .build());
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("User Disabled " + e.getMessage());
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Credentials " + e.getMessage());
        }
    }

}
