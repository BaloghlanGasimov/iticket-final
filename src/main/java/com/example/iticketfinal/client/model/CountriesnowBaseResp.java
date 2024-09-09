package com.example.iticketfinal.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountriesnowBaseResp {
    public boolean error;
    public String msg;
    public List<CountryResp> data;
}
