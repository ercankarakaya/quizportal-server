package com.ercan.repositories;

import com.ercan.models.Question;
import com.ercan.models.Quiz;
import com.ercan.utils.constans.DatabaseConstants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("Select q From Question q Where q.quiz=?1 And q.recordStatus=" + DatabaseConstants.RecordStatus.ACTIVE)
    List<Question> findAllByQuiz(Quiz quiz);
}
