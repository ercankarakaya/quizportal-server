package com.ercan.dtos;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionDto extends BaseDto{
    String answer;
    String content;
    String image;
    String option1;
    String option2;
    String option3;
    String option4;
    QuizDto quiz;
}
