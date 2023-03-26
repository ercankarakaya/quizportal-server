package com.ercan.services;


import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserProfileService {

    void uploadUserProfileImage(Long userId, MultipartFile file);

    byte[] downloadUserProfileImage(Long userId);

    byte[] getImage(String userProfileImageName) throws IOException;

}
