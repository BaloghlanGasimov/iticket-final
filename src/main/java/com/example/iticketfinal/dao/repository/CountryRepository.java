package com.example.iticketfinal.dao.repository;

import com.example.iticketfinal.dao.entity.CountryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<CountryEntity, Long> {
}
