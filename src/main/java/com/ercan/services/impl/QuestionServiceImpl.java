package com.ercan.services.impl;

import com.ercan.dtos.QuestionDto;
import com.ercan.exceptions.QuestionNotFoundException;
import com.ercan.exceptions.QuizNotFoundException;
import com.ercan.models.Question;
import com.ercan.models.Quiz;
import com.ercan.repositories.QuestionRepository;
import com.ercan.repositories.QuizRepository;
import com.ercan.services.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.ercan.utils.constans.DatabaseConstants.RecordStatus.ACTIVE;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private QuizRepository quizRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public QuestionDto save(QuestionDto questionDto) {
        // save question
        Question question = modelMapper.map(questionDto, Question.class);
        Question questionSaved = questionRepository.save(question);
        // update quiz number of questions
        Quiz quiz = quizRepository.getById(questionSaved.getQuiz().getId());
        quiz.setNumberOfQuestions(String.valueOf((Integer.parseInt(quiz.getNumberOfQuestions())+1)));
        quizRepository.save(quiz);
        return modelMapper.map(questionSaved, QuestionDto.class);
    }

    @Override
    public QuestionDto update(QuestionDto questionDto) {
        Question question = modelMapper.map(questionDto, Question.class);
        return modelMapper.map(questionRepository.save(question), QuestionDto.class);
    }

    @Override
    public List<QuestionDto> getAllQuestions() {
        return questionRepository.findAll()
                .stream()
                .filter(item->item.getRecordStatus()== ACTIVE)
                .map(item -> modelMapper.map(item, QuestionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public QuestionDto getQuestionById(Long questionId) {
        Question question = questionRepository.findById(questionId)
                .orElseThrow(() -> new QuestionNotFoundException());
        return modelMapper.map(question, QuestionDto.class);
    }

    @Override
    public List<QuestionDto> getAllQuestionByQuiz(Long quizId) {
        //Quiz quiz = quizRepository.findById(quizId).orElseThrow(() -> new QuizNotFoundException());
        return questionRepository.findAllByQuiz(new Quiz(quizId))
                .stream()
                .map(item -> modelMapper.map(item, QuestionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long questionId) {
        questionRepository.deleteById(questionId);
    }
}
