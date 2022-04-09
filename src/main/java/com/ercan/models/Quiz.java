package com.ercan.models;

import com.ercan.utils.constans.DatabaseConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "quizzes")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Quiz extends BaseModel {

    String title;
    String description;
    String maxMarks;
    String numberOfQuestions;
    Integer enabled;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Where(clause = "record_status=" + DatabaseConstant.RecordStatus.ACTIVE)
    Set<Question> questions = new HashSet<>();

    @Override
    public void onCreate() {
        super.onCreate();
        setEnabled(DatabaseConstant.EnableStatus.PASSIVE);
    }
}
