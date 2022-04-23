package com.ercan.repositories;

import com.ercan.models.Category;
import com.ercan.utils.constans.DatabaseConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("Select c From Category c Where c.recordStatus=" + DatabaseConstants.RecordStatus.ACTIVE + " Order By c.title ASC")
    List<Category> findAll();
}
