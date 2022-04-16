package com.ercan.controllers;

import com.ercan.annotations.LogEntryExit;
import com.ercan.dtos.responses.Response;
import com.ercan.utils.constans.Mappings;
import com.ercan.dtos.UserDto;
import com.ercan.exceptions.UserNotFoundException;
import com.ercan.models.*;
import com.ercan.services.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static com.ercan.utils.constans.DatabaseConstant.Roles.*;
import static com.ercan.enums.ResponseStatusEnum.*;

@RestController
@RequestMapping(Mappings.USER_PATH)
public class UserController {

    /**
     * MODEL - CLASS
     **/
    private final ModelMapper modelMapper;

    /**
     * SERVICES
     **/
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserController(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @LogEntryExit(showArgs = true, showResult = true, unit = ChronoUnit.MILLIS)
    @PostMapping(Mappings.SAVE)
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) throws Exception {
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
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user = userService.save(user, roles);
                Response response = new Response("User saved.", SUCCESS, modelMapper.map(user, UserDto.class));
                return new ResponseEntity<>(response, HttpStatus.CREATED);

            } else {
                Response response = new Response("User cannot be empty!", WARNING, user);
                return new ResponseEntity<>(response, HttpStatus.MULTI_STATUS);
            }
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,ex.getMessage());
            //return ErrorResponse.buildResponseEntity(new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage()));
            //return Response.badRequest(new Response(ex.getMessage(), ERROR));
            //new ResponseEntity<>(new Response(e.getMessage(), BAD_REQUEST), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(Mappings.ALL)
    public ResponseEntity<?> getAllUser() {
        List<UserDto> userDtos = userService.getAll().stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
        Response response = new Response("Data listed.", SUCCESS, userDtos);
        return ResponseEntity.ok(response);
    }

    @GetMapping(Mappings.BY_USERNAME)
    public ResponseEntity<?> getUserByUsername(@PathVariable("username") String username) {
        User user = userService.getUserByUsername(username);
        return Optional.ofNullable(user).map(u -> modelMapper.map(user, UserDto.class))
                .map(ResponseEntity::ok).orElseThrow(() -> new UserNotFoundException());
    }

    @PutMapping(Mappings.BY_ID)
    public ResponseEntity<?> doIgnoreRecord(@PathVariable("id") Long userId) {
        User user=userService.doIgnoreRecord(userId);
        return ResponseEntity.ok(modelMapper.map(user, UserDto.class));
    }

    @DeleteMapping(Mappings.BY_ID)
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long userId) {
        User user = userService.getUserById(userId).orElseThrow(() -> new UserNotFoundException());
        userService.delete(userId);
        return ResponseEntity.ok(modelMapper.map(user, UserDto.class));
    }


}
