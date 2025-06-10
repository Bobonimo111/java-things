package com.willaim.springSecurity.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.willaim.springSecurity.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long>{
    Optional<UserEntity> findByEmail(String email);
    
}
