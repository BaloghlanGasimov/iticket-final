package com.example.iticketfinal.dao.repository;

import com.example.iticketfinal.dao.entity.PaymentHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistoryEntity,Long> {
}
