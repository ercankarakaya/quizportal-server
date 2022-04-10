package com.ercan.repositories;

import com.ercan.models.Question;
import com.ercan.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findAllByQuiz(Quiz quiz);
}
