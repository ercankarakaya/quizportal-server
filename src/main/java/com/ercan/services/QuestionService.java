package com.ercan.services;

import com.ercan.dtos.QuestionDto;

import java.util.List;

public interface QuestionService {
    QuestionDto save(QuestionDto questionDto);

    QuestionDto update(QuestionDto questionDto);

    List<QuestionDto> getAllQuestions();

    QuestionDto getQuestionById(Long questionId);

    List<QuestionDto> getAllQuestionByQuiz(Long quizId);

    void deleteById(Long questionId);
}
