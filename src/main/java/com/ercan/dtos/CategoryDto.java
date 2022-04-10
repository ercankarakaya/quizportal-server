package com.ercan.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.*;

@Data
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryDto extends BaseDto{
    String title;
    String description;
    Set<QuizDto> quizzes = new LinkedHashSet<>();
}
