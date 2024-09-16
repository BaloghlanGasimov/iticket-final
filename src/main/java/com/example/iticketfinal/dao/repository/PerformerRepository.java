package com.example.iticketfinal.dao.repository;

import com.example.iticketfinal.dao.entity.PerformerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerformerRepository extends JpaRepository<PerformerEntity, Long> {
    Optional<PerformerEntity> findByUsername(String username);
}

