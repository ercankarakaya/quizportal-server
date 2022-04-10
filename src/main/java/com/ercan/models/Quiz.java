package com.ercan.models;

import com.ercan.utils.constans.DatabaseConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "quizzes")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Where(clause = "record_status=" + DatabaseConstant.RecordStatus.ACTIVE)
public class Quiz extends BaseModel {

    String title;
    String description;
    String maxMarks;
    String numberOfQuestions;
    Integer enabled;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    Category category;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Question> questions;

    @Override
    public void onCreate() {
        super.onCreate();
        setEnabled(DatabaseConstant.EnableStatus.PASSIVE);
    }

    public Quiz(Long id){
        this.setId(id);
    }
}
