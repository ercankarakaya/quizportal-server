package com.ercan.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.*;

@Data
@RequiredArgsConstructor
@EqualsAndHashCode
@JsonRootName(value = "Category")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryDto extends BaseDto{
    String title;
    String description;
    @JsonIgnore
    Set<QuizDto> quizzes = new LinkedHashSet<>();
}
