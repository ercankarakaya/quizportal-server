package com.ercan.services.impl;

import com.ercan.dtos.QuizDto;
import com.ercan.exceptions.QuizNotFoundException;
import com.ercan.models.Quiz;
import com.ercan.repositories.QuizRepository;
import com.ercan.services.QuizService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public QuizDto save(QuizDto quizDto) {
        Quiz quiz = modelMapper.map(quizDto, Quiz.class);
        return modelMapper.map(quizRepository.save(quiz), QuizDto.class);
    }

    @Override
    public QuizDto update(QuizDto quizDto) {
        Quiz quiz = modelMapper.map(quizDto, Quiz.class);
        return modelMapper.map(quizRepository.save(quiz), QuizDto.class);
    }

    @Override
    public List<QuizDto> getAllQuizzes() {
        return quizRepository.findAll()
                .stream()
                .map(item -> modelMapper.map(item, QuizDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public QuizDto getQuizById(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new QuizNotFoundException());
        return modelMapper.map(quiz, QuizDto.class);
    }

    @Override
    public void deleteById(Long quizId) {
        quizRepository.deleteById(quizId);
    }
}
