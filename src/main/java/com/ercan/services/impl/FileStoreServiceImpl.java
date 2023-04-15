package com.ercan.services.impl;

import com.ercan.models.FileModel;
import com.ercan.repositories.FileRepository;
import com.ercan.services.FileStoreService;
import com.ercan.utils.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Service
public class FileStoreServiceImpl implements FileStoreService {

    @Autowired
    private FileRepository fileRepository;

    @Override
    public FileModel store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FileModel fileModel = new FileModel(fileName,file.getContentType(), ImageUtils.compressImage(file.getBytes()));
        return fileRepository.save(fileModel);
    }

    @Override
    public FileModel getFileById(long id) throws FileNotFoundException {
        return fileRepository.findById(id).orElseThrow(()->new FileNotFoundException());
    }

    @Override
    public FileModel getFileByName(String fileName) throws FileNotFoundException {
        return fileRepository.findByName(fileName).orElseThrow(()->new FileNotFoundException());
    }

    @Override
    public List<FileModel> getAllFiles() {
        return fileRepository.findAll();
    }
}
