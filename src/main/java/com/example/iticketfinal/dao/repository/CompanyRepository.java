package com.example.iticketfinal.dao.repository;

import com.example.iticketfinal.dao.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
}
