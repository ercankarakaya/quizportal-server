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

    @Override
    public User save(User user, Set<UserRole> userRoles) throws Exception {
        if (existsUsersByUsername(user.getUsername())) {
            throw new UserAlreadyExistException("Username already present!");
        }
        if (existsUserByEmail(user.getEmail())) {
            throw new UserAlreadyExistException("Email already present!");
        }

        userRoles.forEach(userRole -> {
            Role role = roleService.save(userRole.getRole());
            userRole.setRole(role);
        });

        user.setUserRoles(userRoles); //user.getUserRoles().addAll(userRoles);
        user = userRepository.save(user);
        return user;

    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }


    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username : " + username));
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public boolean existsUsersByUsername(String username) {
        return userRepository.existsUsersByUsername(username);
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User deactivate(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        user.setEnabled(DatabaseConstant.EnableStatus.PASSIVE);
        return userRepository.save(user);
    }

    @Override
    public User doIgnoreRecord(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        user.setRecordStatus(DatabaseConstant.RecordStatus.PASSIVE);
        return userRepository.save(user);
    }

}
