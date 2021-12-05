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
@Table(name = "users")
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseModel {

    @Column(length = 100)
    String username;
    String password;
    String firstName;
    String lastName;
    String email;
    String phone;
    String profile;
    Integer enabled;

    // one user, many role
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    @Where(clause = "record_status="+DatabaseConstant.RecordStatus.ACTIVE)
    Set<UserRole> userRoles = new HashSet<>();


    public void onCreate() {
        super.onCreate();
        setEnabled(DatabaseConstant.EnableStatus.ACTIVE);
    }

}
