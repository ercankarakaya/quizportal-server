package com.ercan.dtos;

import com.ercan.models.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.*;

@Data
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizDto extends BaseDto{
    String title;
    String description;
    String maxMarks;
    String numberOfQuestions;
    Integer enabled;
    Category category;
    Set<QuestionDto> questions = new HashSet<>();
}
