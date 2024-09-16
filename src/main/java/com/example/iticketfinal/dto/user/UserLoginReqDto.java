package com.example.iticketfinal.dto.user;

import com.example.iticketfinal.dto.phone.PhoneReqDto;
import com.example.iticketfinal.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginReqDto {

    private String name;
    private String surname;
    private String email;
    private LocalDate birthDate;
    private Gender gender;
    private Integer countryId;
    private List<PhoneReqDto> phones;

}
