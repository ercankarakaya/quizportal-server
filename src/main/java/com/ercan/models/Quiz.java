package com.ercan.models;

import com.ercan.utils.constans.DatabaseConstants;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "quizzes")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Quiz extends BaseModel {

    String title;
    @Column(length = 5000)
    String description;
    String maxMarks;
    String numberOfQuestions;
    Integer enabled;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    Category category;

    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Where(clause = "record_status=" + DatabaseConstants.RecordStatus.ACTIVE)
    List<Question> questions;


    public Quiz(Long id){
        this.setId(id);
    }
}
