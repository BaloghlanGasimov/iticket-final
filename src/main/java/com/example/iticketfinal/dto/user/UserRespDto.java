package com.example.iticketfinal.dto.user;

import com.example.iticketfinal.dao.entity.CountryEntity;
import com.example.iticketfinal.dto.country.CountryDto;
import com.example.iticketfinal.dto.phone.PhoneReqDto;
import com.example.iticketfinal.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

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

    private CountryDto country;
    private List<PhoneReqDto> phones;

}
