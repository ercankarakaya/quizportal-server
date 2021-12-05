package com.ercan.services.impl;


import com.ercan.exceptions.UserNotFoundException;
import com.ercan.models.User;
import com.ercan.models.UserRole;
import com.ercan.repositories.RoleRepository;
import com.ercan.repositories.UserRepository;
import com.ercan.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;


    public User create(User user, Set<UserRole> userRoles) throws Exception {
        if (!StringUtils.isEmpty(user.getUsername()) && !StringUtils.isEmpty(user.getEmail())) {
            userRoles.forEach(userRole -> {
                this.roleRepository.save(userRole.getRole());
            });
            user.setUserRoles(userRoles); //user.getUserRoles().addAll(userRoles);
            return this.userRepository.save(user);
        } else {
            throw new Exception("Username or email cannot be empty!");
        }
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }


    public User getUserByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public boolean existsUsersByUsername(String username) {
        return userRepository.existsUsersByUsername(username);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public void deactivate(Long id) {
        userRepository.deactivate(id);
    }

    public void doIgnoreRecord(Long id) {
        userRepository.doIgnoreRecord(id);
    }


}
