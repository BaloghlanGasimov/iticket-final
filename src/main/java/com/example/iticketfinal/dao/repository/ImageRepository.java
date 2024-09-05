package com.example.iticketfinal.dao.repository;

import com.example.iticketfinal.dao.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ImageRepository extends JpaRepository<ImageEntity,Long> {

    Optional<ImageEntity> findByNameAndBucket(String fileName,String bucketName);

}
