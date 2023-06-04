package com.ercan.repositories;

import com.ercan.models.Category;
import com.ercan.models.Quiz;
import com.ercan.utils.constans.DatabaseConstants;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface QuizRepository extends JpaRepository<Quiz, Long>, JpaSpecificationExecutor<Quiz> {

    @Query("Select q From Quiz q Where q.recordStatus=" + DatabaseConstants.RecordStatus.ACTIVE + " Order By q.title ASC")
    List<Quiz> findAll();

    Optional<Quiz> findByTitle(String title);

    @Query("Select q From Quiz q Where q.category.id=?1")
    Optional<Quiz> findByCategoryId(Long categoryId);


    @Query("Select q From Quiz q Where q.category=?1 And q.title IN(?2) And q.recordStatus=?3")
    List<Quiz> findByCategoryAndTitleAndRecordStatus(Category category, Set<String> titles, Integer recordStatus);
}
