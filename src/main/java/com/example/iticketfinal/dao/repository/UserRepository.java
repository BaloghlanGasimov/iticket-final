package com.example.iticketfinal.dao.repository;

import com.example.iticketfinal.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
