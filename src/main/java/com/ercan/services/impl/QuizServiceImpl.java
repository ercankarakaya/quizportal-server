package com.ercan.services.impl;


import com.ercan.dtos.QuizDto;
import com.ercan.exceptions.QuizNotFoundException;
import com.ercan.models.Category;
import com.ercan.models.Quiz;
import com.ercan.repositories.CategoryRepository;
import com.ercan.repositories.QuizRepository;
import com.ercan.services.QuizService;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private CategoryRepository categoryRepository;
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
    public QuizDto getQuizByTitle(String title) {
        Quiz quiz = quizRepository.findByTitle(title).orElseThrow(()->new QuizNotFoundException());
        return modelMapper.map(quiz,QuizDto.class);
    }

    @Override
    public QuizDto getQuizByCategoryId(Long categoryId) {
        Quiz quiz = quizRepository.findByCategoryId(categoryId).orElseThrow(()->new QuizNotFoundException());
        return modelMapper.map(quiz,QuizDto.class);
    }

    @Override
    public List<QuizDto> getQuizByCategoryId(Long categoryId, Set<String> title, Integer recordStatus) {
        Category category = (Category) Hibernate.unproxy(categoryRepository.getById(categoryId));
        return quizRepository.findByCategoryAndTitleAndRecordStatus(category,title,recordStatus)
                .stream()
                .sorted(Comparator.comparing(Quiz::getId))
                .map(item->modelMapper.map(item,QuizDto.class))
                .collect(Collectors.toList());


/*
        List<Quiz> quizList =  quizRepository.findAll(new Specification<Quiz>() {
            @Override
            public Predicate toPredicate(Root<Quiz> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
                List<Predicate> predicates = new ArrayList<>();
                Optional.ofNullable(categoryId).ifPresent(item->predicates.add(builder.and(builder.equal(root.get("category").get("id"),item))));
                Optional.ofNullable(title).ifPresent(item->predicates.add(root.get("title").in(item)));
                Optional.ofNullable(recordStatus).ifPresent(item->predicates.add(builder.and(builder.equal(root.get("recordStatus"),item))));
                return builder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });

        return quizList
                .stream()
                .map(item->modelMapper.map(item,QuizDto.class))
                .collect(Collectors.toList());

 */
    }

    @Override
    public void deleteById(Long quizId) {
        quizRepository.deleteById(quizId);
    }
}
