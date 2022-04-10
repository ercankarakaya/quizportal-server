package com.ercan.models;

import com.ercan.utils.constans.DatabaseConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "categories")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Where(clause = "record_status="+DatabaseConstant.RecordStatus.ACTIVE)
public class Category extends BaseModel {

    String title;
    String description;

    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Set<Quiz> quizzes = new LinkedHashSet<>();
}
