package com.kharrat.cleanarchitecturetest.infrastructure.persistence.repository;

import com.kharrat.cleanarchitecturetest.infrastructure.persistence.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
}
