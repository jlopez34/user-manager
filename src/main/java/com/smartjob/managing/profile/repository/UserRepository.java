package com.smartjob.managing.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import com.smartjob.managing.profile.repository.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
}
