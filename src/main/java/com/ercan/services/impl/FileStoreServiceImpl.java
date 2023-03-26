package com.ercan.services.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.ercan.enums.BucketNameEnum;
import com.ercan.services.FileStoreService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.apache.http.entity.ContentType.*;

@Component
@RequiredArgsConstructor
public class FileStoreServiceImpl implements FileStoreService {

    private static final Logger logger = LogManager.getLogger(FileStoreServiceImpl.class);

    private final AmazonS3 amazonS3Client;

    @Override
    public void save(String path, String fileName, Optional<Map<String, String>> optMetaData, InputStream inputStream) {
        try {

            ObjectMetadata metadata = new ObjectMetadata();
            optMetaData.ifPresent(data->{
                if (!data.isEmpty()){
                    data.forEach(metadata::addUserMetadata);
                }
            });

            amazonS3Client.putObject(path,fileName,inputStream,metadata);

        }catch(AmazonServiceException ex) {
            throw new IllegalStateException("Failed to store file to amazonS3 ->", ex);
        }
        catch (Exception ex){
            logger.error("Error FileStoreServiceImpl.save -> ",ex);
        }
    }

    @Override
    public byte[] download(String path, String key) {
        try {
            S3Object object = amazonS3Client.getObject(path,key);
            return IOUtils.toByteArray(object.getObjectContent());
        }catch (AmazonServiceException | IOException ex){
            throw new IllegalStateException("Failed to download file to amazonS3 ->", ex);
        }catch (Exception ex){
            logger.error("Error FileStoreServiceImpl.download -> ",ex);
        }
        return new byte[0];
    }

    @Override
    public Map<String, String> extractMetadata(MultipartFile file) {
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }

    @Override
    public void isImage(MultipartFile file) {
        if(!Arrays.asList(
                IMAGE_JPEG.getMimeType(),
                IMAGE_GIF.getMimeType(),
                IMAGE_PNG.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("File must be an image [" + file.getContentType() + "]");
        }
    }

    @Override
    public void isFileEmpty(MultipartFile file) {
        if (file.isEmpty())
            throw new IllegalStateException("Cannot upload empty file [" + file.getSize() + "]");
    }

    @Override
    public byte[] getObjectContent(String bucketName,String key) throws IOException {
        S3Object s3object = amazonS3Client.getObject(bucketName, key);
        S3ObjectInputStream inputStream = s3object.getObjectContent();
        return inputStream.readAllBytes();
    }

}
