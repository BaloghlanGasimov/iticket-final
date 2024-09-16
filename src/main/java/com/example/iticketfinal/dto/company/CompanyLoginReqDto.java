package com.example.iticketfinal.dto.company;

import com.example.iticketfinal.dto.image.ImageDto;
import com.example.iticketfinal.dto.phone.PhoneReqDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyLoginReqDto {
    private String name;
    private String email;
    private ImageDto logo;
    private String website;
    private String description;
    private List<PhoneReqDto> phones;
}