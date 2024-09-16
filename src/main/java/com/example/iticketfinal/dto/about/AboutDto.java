package com.example.iticketfinal.dto.about;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AboutDto {
    @NotBlank
    private String title;
    @NotBlank
    private String description;
}
