package com.example.iticketfinal.dao.repository;

import com.example.iticketfinal.dao.entity.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity,Long> {
}
