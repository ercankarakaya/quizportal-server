package com.ercan.services;

import com.ercan.models.FileModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FileStoreService {

    FileModel store(MultipartFile file) throws IOException;

    FileModel getFileById(long id) throws FileNotFoundException;

    FileModel getFileByName(String fileName) throws FileNotFoundException;

    List<FileModel> getAllFiles();
}
