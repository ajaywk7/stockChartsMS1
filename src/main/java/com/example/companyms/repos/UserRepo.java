package com.example.companyms.repos;

import com.example.companyms.Entities.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserData,Long> {
    public Optional<UserData> findByUsername(String name);
}
