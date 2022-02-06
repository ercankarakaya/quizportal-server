package com.ercan.models;

import com.ercan.utils.constans.DatabaseConstant;
import com.ercan.security.Authority;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
@RequiredArgsConstructor
@EqualsAndHashCode(of = "id")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User extends BaseModel implements UserDetails {

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


    @Override
    public void onCreate() {
        super.onCreate();
        setEnabled(DatabaseConstant.EnableStatus.ACTIVE);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> authoritySet=new HashSet<>();
        this.userRoles.stream()
                .map(UserRole::getRole)
                .map(Role::getRoleName)
                .map(Authority::new)
                .forEach(authoritySet::add);

        return authoritySet;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
