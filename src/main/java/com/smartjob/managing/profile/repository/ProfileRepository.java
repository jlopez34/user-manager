package com.smartjob.managing.profile.repository;

import com.smartjob.managing.profile.repository.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    boolean existsByEmail(String email);
}
