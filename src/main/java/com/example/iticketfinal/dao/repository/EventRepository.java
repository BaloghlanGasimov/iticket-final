package com.example.iticketfinal.dao.repository;

import com.example.iticketfinal.dao.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<EventEntity, Long> {
    List<EventEntity> findAllByExpiredIsFalse();
}
