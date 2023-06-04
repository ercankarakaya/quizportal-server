package com.ercan.controllers;

import com.ercan.dtos.QuizDto;
import com.ercan.services.QuizService;
import com.ercan.utils.constans.Mappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping(Mappings.QUIZ_PATH)
public class QuizController {

    @Autowired
    private QuizService quizService;


    @PostMapping(Mappings.SAVE)
    public ResponseEntity<?> addQuiz(@RequestBody QuizDto quizDto) {
        return ResponseEntity.ok(quizService.save(quizDto));
    }

    @PutMapping(Mappings.UPDATE)
    public ResponseEntity<?> updateQuiz(@RequestBody QuizDto quizDto) {
        return ResponseEntity.ok(quizService.update(quizDto));
    }

    @GetMapping(Mappings.BY_ID)
    public ResponseEntity<?> getQuizById(@PathVariable("id") Long quizId) {
        return ResponseEntity.ok(quizService.getQuizById(quizId));
    }

    @GetMapping
    public ResponseEntity<?> getQuizByTitle(@RequestParam("title") String title){
        return ResponseEntity.ok(quizService.getQuizByTitle(title));
    }

    @GetMapping(Mappings.ALL)
    public ResponseEntity<?> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    @GetMapping(Mappings.BY_CATEGORY_ID)
    public ResponseEntity<?> getQuizByCategoryId(@PathVariable Long categoryId,
                                                 @RequestParam Set<String> title,
                                                 @RequestParam Integer recordStatus){
      return ResponseEntity.ok(quizService.getQuizByCategoryId(categoryId,title,recordStatus));
    }

    @DeleteMapping(Mappings.BY_ID)
    public ResponseEntity<?> deleteQuiz(@PathVariable("id") Long quizId) {
        quizService.deleteById(quizId);
        return new ResponseEntity<>(quizId+" record deleted.", HttpStatus.OK);
    }

}
