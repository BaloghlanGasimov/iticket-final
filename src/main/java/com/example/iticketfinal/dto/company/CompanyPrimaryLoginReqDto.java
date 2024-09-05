package com.example.iticketfinal.dto.company;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyPrimaryLoginReqDto {
    @NotNull
    private String name;
    @Email
    private String email;
    @NotNull
    private MultipartFile logoImage;
}
