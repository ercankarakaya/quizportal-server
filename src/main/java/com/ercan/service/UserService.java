package com.ercan.service;

import com.ercan.model.User;
import com.ercan.model.UserRole;

import java.util.Set;

public interface UserService {
    User createUser(User user, Set<UserRole> userRoles) throws Exception;
}
