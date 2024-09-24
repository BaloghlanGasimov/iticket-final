package com.example.iticketfinal.dao.repository;

import com.example.iticketfinal.dao.entity.PaymentHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentHistoryRepository extends JpaRepository<PaymentHistoryEntity, Long> {
    List<PaymentHistoryEntity> findByUserId(Long userId);
}
