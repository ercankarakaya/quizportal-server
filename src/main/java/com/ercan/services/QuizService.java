package com.ercan.services;

import com.ercan.dtos.QuizDto;

import java.util.List;

public interface QuizService {
    QuizDto save(QuizDto quizDto);

    QuizDto update(QuizDto quizDto);

    List<QuizDto> getAllQuizzes();

    QuizDto getQuizById(Long quizId);

    void deleteById(Long quizId);
}
