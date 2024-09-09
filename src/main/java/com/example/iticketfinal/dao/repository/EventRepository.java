package com.example.iticketfinal.dao.repository;

import com.example.iticketfinal.dao.entity.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<EventEntity,Long> {
}
