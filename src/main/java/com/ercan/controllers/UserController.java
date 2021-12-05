package com.ercan.controllers;

import com.ercan.constans.Mappings;
import com.ercan.dtos.UserDto;
import com.ercan.exceptions.UserNotFoundException;
import com.ercan.models.*;
import com.ercan.response.*;
import com.ercan.services.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Lists;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;

import static com.ercan.constans.DatabaseConstant.Roles.*;
import static com.ercan.enums.ResponseStatusEnum.*;

@Slf4j
@RestController
@RequestMapping(Mappings.USER_PATH)
@RequiredArgsConstructor
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


    @PostMapping(Mappings.SAVE)
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) throws Exception {
        try {
            User user = modelMapper.map(userDto, User.class);
            if (Objects.nonNull(user)) {
                if (userService.existsUsersByUsername(user.getUsername())) {
                    return new ResponseEntity<>(new Response("User already present!", WARNING), HttpStatus.MULTI_STATUS);
                } else {
                    Role role = new Role();
                    role.setRoleName(ROLE_USER);
                    Set<UserRole> roles = new HashSet<>();
                    UserRole userRole = new UserRole();
                    userRole.setUser(user);
                    userRole.setRole(role);
                    roles.add(userRole);
                    user = userService.create(user, roles);
                    Response response = new Response("User saved.", SUCCESS, user);
                    return new ResponseEntity<>(response, HttpStatus.CREATED);
                }

            } else {
                Response response = new Response("User cannot be empty!", WARNING, user);
                return new ResponseEntity<>(response, HttpStatus.MULTI_STATUS);
            }
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ErrorResponse.buildResponseEntity(new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage()));
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
                .map(ResponseEntity::ok).orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    @PutMapping(Mappings.BY_ID)
    public ResponseEntity<?> doIgnoreRecord(@PathVariable Long id) {
        User user = userService.getUserById(id).orElseThrow(() -> new UserNotFoundException("User not found!"));
        userService.doIgnoreRecord(id);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        Response response = new Response("User registration ignored.", SUCCESS, userDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping(Mappings.BY_ID)
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User user = userService.getUserById(id).orElseThrow(() -> new UserNotFoundException("User not found!"));
        userService.delete(id);
        UserDto userDto = modelMapper.map(user, UserDto.class);
        Response response = new Response("Record deleted.", SUCCESS, userDto);
        return ResponseEntity.ok(response);
    }


}
