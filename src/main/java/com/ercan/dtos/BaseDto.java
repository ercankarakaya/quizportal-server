package com.ercan.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Calendar;

@Data
@RequiredArgsConstructor
@MappedSuperclass
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
