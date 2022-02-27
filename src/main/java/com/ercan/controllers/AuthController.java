package com.ercan.controllers;

import com.ercan.annotations.LogEntryExit;
import com.ercan.aspects.LoggingAspect;
import com.ercan.dtos.UserDto;
import com.ercan.dtos.LoginRequest;
import com.ercan.models.Role;
import com.ercan.models.User;
import com.ercan.models.UserRole;
import com.ercan.response.ErrorResponse;
import com.ercan.response.Response;
import com.ercan.services.AuthService;
import com.ercan.utils.constans.Mappings;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.ercan.enums.ResponseStatusEnum.SUCCESS;
import static com.ercan.enums.ResponseStatusEnum.WARNING;
import static com.ercan.utils.constans.DatabaseConstant.Roles.ROLE_USER;


@CrossOrigin("*")
@RestController
@RequestMapping(Mappings.AUTH)
public class AuthController {

    private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

    /**
     * MODEL - CLASS
     **/
    private final ModelMapper modelMapper;

    /**
     * SERVICES
     **/
    @Autowired
    private AuthService authService;


    public AuthController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @LogEntryExit(showArgs = true, showResult = true, unit = ChronoUnit.MILLIS)
    @PostMapping(Mappings.SIGNUP)
    public ResponseEntity<?> signup(@RequestBody UserDto userDto) throws Exception {
        try {
            User user = modelMapper.map(userDto, User.class);
            if (Objects.nonNull(user)) {

                Role role = new Role();
                role.setRoleName(ROLE_USER);

                Set<UserRole> roles = new HashSet<>();
                UserRole userRole = new UserRole();
                userRole.setUser(user);
                userRole.setRole(role);
                roles.add(userRole);

                user.setProfile("default.png");
                user = authService.signup(user, roles);
                Response response = new Response("User registered.", SUCCESS, modelMapper.map(user, UserDto.class));
                return new ResponseEntity<>(response, HttpStatus.CREATED);

            } else {
                Response response = new Response("User cannot be empty!", WARNING, user);
                return new ResponseEntity<>(response, HttpStatus.MULTI_STATUS);
            }
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
        }
    }

    @PostMapping(Mappings.SIGNIN)
    public ResponseEntity<?> signin(@RequestBody LoginRequest loginRequest) throws Exception {
        return ResponseEntity.ok(authService.signin(loginRequest));
    }

    @GetMapping(Mappings.CURRENT_USER)
    public ResponseEntity<?> getCurrentUser() {
        return ResponseEntity.ok(authService.getCurrentUser());
    }

}
