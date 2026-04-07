package com.kharrat.cleanarchitecturetest.infrastructure.persistence.repository;


import com.kharrat.cleanarchitecturetest.infrastructure.persistence.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
}
