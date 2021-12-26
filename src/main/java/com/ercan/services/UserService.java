package com.ercan.services;

import com.ercan.models.User;
import com.ercan.models.UserRole;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {
    User save(User user, Set<UserRole> userRoles) throws Exception;

    User getUserByUsername(String username);

    Optional<User> getUserById(Long id);

    boolean existsUsersByUsername(String username);

    List<User> getAll();

    void delete(Long id);

    void deactivate(Long id);

    void doIgnoreRecord(Long id);

    boolean checkUserExist(User user);

}
