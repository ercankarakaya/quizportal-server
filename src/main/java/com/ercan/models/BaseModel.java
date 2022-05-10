package com.ercan.models;

import com.ercan.utils.constans.DatabaseConstants;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Calendar;
import java.util.UUID;

/**
 * @author Ercan Karakaya 05.12.2021
 */

@Data
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
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
    public void onCreate() {
        setRecordStatus(DatabaseConstants.RecordStatus.ACTIVE);
        //setId(generateUniqueId());
    }

    public Long generateUniqueId() {
        long val = -1;
        do {
            val = UUID.randomUUID().getMostSignificantBits();
        } while (val < 0);
        return val;
    }

    public Long generateUniqueId2() {
        return (long) (Math.random() * Long.MAX_VALUE);
    }

    public Long generateUniqueId3() {
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }
}
