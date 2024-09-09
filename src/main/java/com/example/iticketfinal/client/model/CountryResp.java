package com.example.iticketfinal.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryResp {
    public String name;
    public String iso2;
    @JsonProperty("long")
    public int mylong;
    public int lat;
}
