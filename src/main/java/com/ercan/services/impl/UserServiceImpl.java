package com.ercan.services.impl;


import com.ercan.dtos.responses.FileResponse;
import com.ercan.exceptions.UserAlreadyExistException;
import com.ercan.exceptions.UserNotFoundException;
import com.ercan.models.FileModel;
import com.ercan.models.Role;
import com.ercan.models.User;
import com.ercan.models.UserRole;
import com.ercan.repositories.UserRepository;
import com.ercan.services.FileStoreService;
import com.ercan.services.RoleService;
import com.ercan.services.UserService;
import com.ercan.utils.constans.DatabaseConstants;
import com.ercan.utils.constans.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
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
    @Autowired
    private FileStoreService fileStoreService;

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
    public User update(User user) {
        return userRepository.save(user);
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
        return Optional.ofNullable(userRepository.findById(id).orElseThrow(() -> new UserNotFoundException()));
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
        user.setEnabled(DatabaseConstants.EnableStatus.PASSIVE);
        return userRepository.save(user);
    }

    @Override
    public User doIgnoreRecord(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());
        user.setRecordStatus(DatabaseConstants.RecordStatus.PASSIVE);
        return userRepository.save(user);
    }

    @Override
    public User uploadProfileImage(Long id, MultipartFile file) throws IOException {
        User user = getUserById(id).get();
        FileModel fileModel = fileStoreService.store(file);
        user.setProfile(fileModel.getName());
        return update(user);
    }

    @Override
    public FileResponse getProfileImageInfoByFileName(Long userId) throws FileNotFoundException {
        User user = getUserById(userId).get();
        FileModel fileModel = fileStoreService.getFileByName(user.getProfile());

        String url = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(Mappings.USER_PATH+"/image/view/")
                .path(user.getId()+"/")
                .path(fileModel.getId()+"")
                .toUriString();

        FileResponse fileResponse = new FileResponse();
        fileResponse.setName(fileModel.getName());
        fileResponse.setType(fileModel.getType());
        fileResponse.setSize(fileModel.getData().length);
        fileResponse.setData(fileModel.getData());
        fileResponse.setUrl(url);
        return fileResponse;
    }

    @Override
    public byte[] getProfileImage(String fileName) throws FileNotFoundException {
        FileModel fileModel = fileStoreService.getFileByName(fileName);
        return fileModel.getData();
    }

}
