package com.ercan.repositories;

import com.ercan.models.Quiz;
import com.ercan.utils.constans.DatabaseConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    @Query("Select q From Quiz q Where q.recordStatus=" + DatabaseConstants.RecordStatus.ACTIVE + " Order By q.title ASC")
    List<Quiz> findAll();
}
