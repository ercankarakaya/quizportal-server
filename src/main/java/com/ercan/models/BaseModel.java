package com.ercan.models;

import com.ercan.utils.constans.DatabaseConstant;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Calendar;

/**
 * @author Ercan Karakaya 05.12.2021
 */

@Data
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class BaseModel implements Serializable {

    static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    Calendar createdDate;

    @CreatedBy
    String createdBy;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    Calendar lastModifiedDate;

    @LastModifiedBy
    String lastModifiedBy;

    Integer recordStatus;

    @PrePersist
    public void onCreate(){
        recordStatus= DatabaseConstant.RecordStatus.ACTIVE;
    }

}
