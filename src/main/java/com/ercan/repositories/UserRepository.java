package com.ercan.repositories;

import com.ercan.constans.DatabaseConstant;
import com.ercan.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);

    boolean existsUsersByUsername(String username);

    @Modifying
    @Query("update User u set u.enabled="+ DatabaseConstant.EnableStatus.PASSIVE+" where u.id=?1")
    void deactivate(Long id);

    @Modifying
    @Query("update User u set u.recordStatus="+ DatabaseConstant.RecordStatus.PASSIVE+" where u.id=?1")
    void doIgnoreRecord(Long id);
}
