package com.example.iticketfinal.dto.token;

import com.example.iticketfinal.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseDto {
    private String token;
    private Roles role;
}
