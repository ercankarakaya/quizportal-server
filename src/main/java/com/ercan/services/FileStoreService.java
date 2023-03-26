package com.ercan.services;

import com.amazonaws.services.s3.model.S3ObjectInputStream;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

public interface FileStoreService {

    void save(String path, String fileName, Optional<Map<String,String>> metaData, InputStream inputStream);

    byte[] download(String path,String key);

    Map<String, String> extractMetadata(MultipartFile file);

    void isImage(MultipartFile file);

    void isFileEmpty(MultipartFile file);

    byte[] getObjectContent(String bucketName,String key) throws IOException;

}
