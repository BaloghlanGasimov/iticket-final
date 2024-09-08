package com.example.iticketfinal.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPrimaryLoginReqDto {
    @NotNull
    private String name;
    @NotNull
    private String surname;
    @Email
    private String email;
//    @Schema(type = "string", format = "binary")
//    private MultipartFile test;
//    @PostMapping(value = "/primary",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    @PostMapping(value = "/primary",consumes = { "multipart/form-data" })

}
