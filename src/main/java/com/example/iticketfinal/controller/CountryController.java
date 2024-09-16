package com.example.iticketfinal.controller;

import com.example.iticketfinal.client.CountriesnowClient;
import com.example.iticketfinal.client.model.CountriesnowBaseResp;
import com.example.iticketfinal.dto.country.CountryDto;
import com.example.iticketfinal.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountriesnowClient countriesnowClient;
    private final CountryService countryService;

    @GetMapping("/client")
    public CountriesnowBaseResp getCountries() {
        return countriesnowClient.getCountries();
    }

    @GetMapping("/db")
    public List<CountryDto> saveAndGetCountries() {
        return countryService.saveAndGetCountries();
    }

}
