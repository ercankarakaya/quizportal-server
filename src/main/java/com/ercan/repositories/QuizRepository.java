package com.ercan.repositories;

import com.ercan.models.Category;
import com.ercan.models.Quiz;
import com.ercan.utils.constans.DatabaseConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    @Query("Select q From Quiz q Where q.recordStatus=" + DatabaseConstants.RecordStatus.ACTIVE + " Order By q.title ASC")
    List<Quiz> findAll();

    Optional<Quiz> findByTitle(String title);

    @Query("Select q From Quiz q Where q.category.id=?1")
    Optional<Quiz> findByCategoryId(Long categoryId);

    Optional<Quiz> findByCategoryAndTitleAndRecordStatus(Category category, String title, Integer recordStatus);
}
