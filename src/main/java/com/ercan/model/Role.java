package com.ercan.model;

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
@Table(name = "roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role extends BaseModel {

    String roleName;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "role")
    Set<UserRole> userRoles = new HashSet<>();
}
