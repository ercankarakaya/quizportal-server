package com.ercan.services;

import com.ercan.dtos.responses.FileResponse;
import com.ercan.models.User;
import com.ercan.models.UserRole;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {
    User save(User user, Set<UserRole> userRoles) throws Exception;

    User update(User user);

    User getUserByUsername(String username);

    Optional<User> getUserById(Long id);

    boolean existsUsersByUsername(String username);

    boolean existsUserByEmail(String email);

    List<User> getAll();

    void delete(Long id);

    User deactivate(Long id);

    User doIgnoreRecord(Long id);

    User uploadProfileImage(Long id,MultipartFile file) throws IOException;

    FileResponse getProfileImageInfoByFileName(Long userId) throws FileNotFoundException;

    byte[] getProfileImage(String fileName) throws FileNotFoundException;

}
