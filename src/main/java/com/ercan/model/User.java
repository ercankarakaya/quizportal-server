package com.ercan.model;

import com.ercan.constans.DatabaseConstant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@RequiredArgsConstructor
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

    // user many role
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    Set<UserRole> userRoles = new HashSet<>();


    public void onCreate() {
        setEnabled(DatabaseConstant.EnableStatus.ACTIVE);
    }

}
