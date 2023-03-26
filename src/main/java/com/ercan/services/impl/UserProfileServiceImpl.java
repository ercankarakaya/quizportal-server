package com.ercan.services.impl;

import com.ercan.dtos.UserDto;
import com.ercan.enums.BucketNameEnum;
import com.ercan.exceptions.UserNotFoundException;
import com.ercan.models.User;
import com.ercan.services.FileStoreService;
import com.ercan.services.UserProfileService;
import com.ercan.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {

    private final UserService userService;
    private final FileStoreService fileStoreService;

    @Override
    public void uploadUserProfileImage(Long userId, MultipartFile file) {

        fileStoreService.isFileEmpty(file);
        fileStoreService.isImage(file);

        User user = getUserById(userId);
        Map<String,String> metadata = fileStoreService.extractMetadata(file);
        String path = String.format("%s/%s", BucketNameEnum.PROFILE_IMAGE.getBucketName(),user.getId());
        String fileName = String.format("%s-%s",file.getOriginalFilename(), UUID.randomUUID());

        try {
            fileStoreService.save(path,fileName, Optional.of(metadata),file.getInputStream());
            user.setProfile(fileName);
            userService.update(user);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public byte[] downloadUserProfileImage(Long userId) {
        User user = getUserById(userId);
        String path = String.format("%s/%s",BucketNameEnum.PROFILE_IMAGE.getBucketName(),user.getId());
        return Optional.ofNullable(user.getProfile())
                .map(key->fileStoreService.download(path,key))
                .orElse(new byte[0]);
    }

    @Override
    public byte[] getImage(String userProfileImageName) throws IOException {
        return fileStoreService.getObjectContent(BucketNameEnum.PROFILE_IMAGE.getBucketName(), userProfileImageName);
    }

    private User getUserById(Long userId) {
        return userService
                .getUserById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found! "));
    }

}
