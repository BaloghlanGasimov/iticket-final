package com.example.iticketfinal.dto.user;

import com.example.iticketfinal.dao.entity.CountryEntity;
import com.example.iticketfinal.dto.country.CountryDto;
import com.example.iticketfinal.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginReqDto {

    @NotNull
    private String name;
    @NotNull
    private String surname;
    @Email
    private String email;
    private String phone;
    private LocalDate birthDate;
    private Gender gender;
    private CountryDto country;
}
