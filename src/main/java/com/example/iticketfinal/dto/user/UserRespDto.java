package com.example.iticketfinal.dto.user;

import com.example.iticketfinal.dao.entity.CountryEntity;
import com.example.iticketfinal.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRespDto {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private LocalDate birthDate;
    private Gender gender;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private CountryEntity country;

}
