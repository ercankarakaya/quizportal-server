package com.ercan.controllers;

import com.ercan.dtos.QuestionDto;
import com.ercan.dtos.QuizDto;
import com.ercan.models.Quiz;
import com.ercan.services.QuestionService;
import com.ercan.services.QuizService;
import com.ercan.utils.constans.Mappings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping(Mappings.QUESTION_PATH)
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuizService quizService;


    @PostMapping(Mappings.SAVE)
    public ResponseEntity<?> addQuestion(@RequestBody QuestionDto questionDto) {
        return ResponseEntity.ok(questionService.save(questionDto));
    }

    @PutMapping(Mappings.UPDATE)
    public ResponseEntity<?> updateQuestion(@RequestBody QuestionDto questionDto) {
        return ResponseEntity.ok(questionService.update(questionDto));
    }

    @GetMapping(Mappings.BY_ID)
    public ResponseEntity<?> getQuestionById(@PathVariable("id") Long questionId) {
        return ResponseEntity.ok(questionService.getQuestionById(questionId));
    }

    @GetMapping(Mappings.ALL)
    public ResponseEntity<?> getAllQuizzes() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @GetMapping(Mappings.BY_QUIZ_ID)
    public ResponseEntity<?> getAllQuestionByQuiz(@PathVariable Long quizId) {
        QuizDto quizDto = quizService.getQuizById(quizId);
        List questionDtoList = quizDto.getQuestions();
        if (questionDtoList.size() > Integer.parseInt(quizDto.getNumberOfQuestions())) {
            questionDtoList = questionDtoList.subList(0, Integer.parseInt(quizDto.getNumberOfQuestions()) + 1);
        }
        Collections.shuffle(questionDtoList); //randomly list
        return ResponseEntity.ok(questionDtoList);
    }

    @DeleteMapping(Mappings.BY_ID)
    public ResponseEntity<?> deleteQuestion(@PathVariable("id") Long questionId) {
        questionService.deleteById(questionId);
        return new ResponseEntity<>(questionId + " record deleted.", HttpStatus.OK);
    }

}
