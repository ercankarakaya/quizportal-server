package com.ercan.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.*;

@Getter
@Setter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizDto extends BaseDto{
    String title;
    String description;
    String maxMarks;
    String numberOfQuestions;
    Integer enabled;
    //@JsonProperty(value = "Category")
    CategoryDto category;
    @JsonIgnore
   List<QuestionDto> questions;

}


/**
 * @JsonInclude(JsonInclude.Include.NON_NULL) : It does not return null fields.
 * @JsonRootName(value = "Quiz"): The @JsonRootName annotation is used, if wrapping is enabled, to specify the name of the root wrapper to be used.
 * {
 * "Quiz":{
 * "id": 3637145082458164007,
 * "createdDate": "2022-04-10T14:49:22.258+00:00",
 * "createdBy": "admin",
 * "recordStatus": 1,
 * "title": "Quiz6",
 * "description": "desc6",
 * "maxMarks": "maxMarks6",
 * "numberOfQuestions": "numberOfQuestions6",
 * "enabled": 0,
 * "questions": [],
 * "category": null
 * }
 * }
 *
 * @JsonTypeInfo(include= JsonTypeInfo.As.WRAPPER_OBJECT, use= JsonTypeInfo.Id.NAME): for @JsonRootName annotation without config class bean method.
 */

