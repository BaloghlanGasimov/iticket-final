package com.example.iticketfinal.dto.company;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrimaryCompanyLoginReqDto {
    private String name;
    private String email;
    private String logo;
}
