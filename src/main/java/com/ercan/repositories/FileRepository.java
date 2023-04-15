package com.ercan.repositories;

import com.ercan.models.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<FileModel,Long> {

    Optional<FileModel> findByName(String name);
}
