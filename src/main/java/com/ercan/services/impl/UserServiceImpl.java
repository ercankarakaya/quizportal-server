package com.ercan.services.impl;


import com.ercan.exceptions.UserAlreadyExistException;
import com.ercan.exceptions.UserNotFoundException;
import com.ercan.models.Role;
import com.ercan.models.User;
import com.ercan.models.UserRole;
import com.ercan.repositories.UserRepository;
import com.ercan.services.RoleService;
import com.ercan.services.UserService;
import com.ercan.utils.constans.DatabaseConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleService roleService;


    public User save(User user, Set<UserRole> userRoles) throws Exception {
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

    public List<User> getAll() {
        return userRepository.findAll();
    }


    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username : " + username));
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public boolean existsUsersByUsername(String username) {
        return userRepository.existsUsersByUsername(username);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public User deactivate(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found!"));
        user.setEnabled(DatabaseConstant.EnableStatus.PASSIVE);
        return userRepository.save(user);
    }

    public User doIgnoreRecord(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found!"));
        user.setRecordStatus(DatabaseConstant.RecordStatus.PASSIVE);
        return userRepository.save(user);
    }

}
