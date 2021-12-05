package com.ercan.models;

import com.ercan.constans.DatabaseConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "roles")
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role extends BaseModel {

    String roleName;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "role")
    @Where(clause = "record_status="+ DatabaseConstant.RecordStatus.ACTIVE)
    Set<UserRole> userRoles = new HashSet<>();

}
