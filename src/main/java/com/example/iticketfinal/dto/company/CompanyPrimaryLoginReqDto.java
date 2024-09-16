package com.example.iticketfinal.dto.company;

import com.example.iticketfinal.dto.image.ImageDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyPrimaryLoginReqDto {
    @NotBlank
    private String name;
    @Email
    private String email;
    @NotNull
    private ImageDto logo;
}
