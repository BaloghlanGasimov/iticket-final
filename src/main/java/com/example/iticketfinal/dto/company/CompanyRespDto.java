package com.example.iticketfinal.dto.company;

import com.example.iticketfinal.dao.entity.EventEntity;
import com.example.iticketfinal.dao.entity.PhoneEntity;
import com.example.iticketfinal.dto.image.ImageDto;
import com.example.iticketfinal.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyRespDto {
    private Long id;
    private String name;
    private String email;
    private String website;
    private String description;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ImageDto logo;
    private List<PhoneEntity> phones;
    private List<EventEntity> events;
}