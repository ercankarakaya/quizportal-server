package com.ercan.service.impl;

import com.ercan.model.User;
import com.ercan.model.UserRole;
import com.ercan.repository.RoleRepository;
import com.ercan.repository.UserRepository;
import com.ercan.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {
        User localUser = this.userRepository.findByUsername(user.getUsername());
        if (localUser != null) {
            log.error("User is already there!");
            throw new Exception("User already present!");
        } else {
            userRoles.forEach(userRole -> {
                this.roleRepository.save(userRole.getRole());
            });
            user.getUserRoles().addAll(userRoles);
            localUser = this.userRepository.save(user);
            log.info("User succesfully saved.");
        }
        return localUser;
    }

}
