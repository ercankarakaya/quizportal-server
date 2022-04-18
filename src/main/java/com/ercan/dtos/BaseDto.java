package com.ercan.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Calendar;

@Data
@MappedSuperclass
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonIgnoreProperties({"lastModifiedDate","lastModifiedBy"})
public class BaseDto implements Serializable {
    Long id;
    Calendar createdDate;
    String createdBy;
    Calendar lastModifiedDate;
    String lastModifiedBy;
    Integer recordStatus;
}

/**
 * @JsonFormat annotation
 * In Spring Boot application, to add annotation on the date field with the same format
 * Example -> We can define it on any variable.
       @JsonFormat(pattern = "yyyy/MM/dd")
       private Date dueDate;
 * Another way -> spring.jackson.date-format=yyyy/MM/dd  (we can define in application.properties)
 * Or -> builder.dateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")); ( In AppConfig Jackson2ObjectMapperBuilder bean)
 */
