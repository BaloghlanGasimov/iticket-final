package com.example.iticketfinal.dto.place;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceDto {
    private Long id;
    @NotNull
    private String name;
    private String location;
    @NotNull
    private String address;
}
