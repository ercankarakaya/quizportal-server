package com.ercan.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;


@Data
@Entity
@Table(name = "questions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Question extends BaseModel {

    String answer;
    @Column(length = 5000)
    String content;
    String image;
    String option1;
    String option2;
    String option3;
    String option4;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_id")
    Quiz quiz;
}
