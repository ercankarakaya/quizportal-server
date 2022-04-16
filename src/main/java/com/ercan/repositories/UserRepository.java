package com.ercan.repositories;

import com.ercan.utils.constans.DatabaseConstants;
import com.ercan.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsUsersByUsername(String username);

    boolean existsUserByEmail(String email);

    @Modifying
    @Query("update User u set u.enabled=" + DatabaseConstants.EnableStatus.PASSIVE +
            ", u.lastModifiedBy=?2,u.lastModifiedDate=?3 where u.id=?1")
    void deactivate(Long id, String modifiedByUser, Calendar modifiedDate);

    @Modifying
    @Query("update User u set u.recordStatus=" + DatabaseConstants.RecordStatus.PASSIVE +
            ", u.lastModifiedBy=?2,u.lastModifiedDate=?3 where u.id=?1")
    void doIgnoreRecord(Long id, String modifiedByUser, Calendar modifiedDate);
}
