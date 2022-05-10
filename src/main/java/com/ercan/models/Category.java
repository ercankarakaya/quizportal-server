package com.ercan.models;

import com.ercan.utils.constans.DatabaseConstants;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "categories")
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category extends BaseModel {

    String title;
    String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    @Where(clause = "record_status="+ DatabaseConstants.RecordStatus.ACTIVE)
    Set<Quiz> quizzes = new LinkedHashSet<>();
}
