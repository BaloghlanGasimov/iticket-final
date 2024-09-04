package com.example.iticketfinal.dto.company;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrimaryCompanyLoginReqDto {
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private MultipartFile logoImage;
}
