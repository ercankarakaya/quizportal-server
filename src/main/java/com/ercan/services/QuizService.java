package com.ercan.services;

import com.ercan.dtos.QuizDto;
import com.ercan.models.Quiz;

import java.util.List;
import java.util.Set;

public interface QuizService {

    QuizDto save(QuizDto quizDto);

    QuizDto update(QuizDto quizDto);

    List<QuizDto> getAllQuizzes();

    QuizDto getQuizById(Long quizId);

    QuizDto getQuizByTitle(String title);

    QuizDto getQuizByCategoryId(Long categoryId);

    List<QuizDto> getQuizByCategoryId(Long categoryId, Set<String> title, Integer recordStatus);

    void deleteById(Long quizId);
}
