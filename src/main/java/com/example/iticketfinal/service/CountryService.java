package com.example.iticketfinal.service;

import com.example.iticketfinal.client.CountriesnowClient;
import com.example.iticketfinal.client.model.CountriesnowBaseResp;
import com.example.iticketfinal.client.model.CountryResp;
import com.example.iticketfinal.dao.entity.CountryEntity;
import com.example.iticketfinal.dao.repository.CountryRepository;
import com.example.iticketfinal.dto.country.CountryDto;
import com.example.iticketfinal.mapper.CountryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CountryService {
    private final CountriesnowClient countriesnowClient;
    private final CountryMapper countryMapper;
    private final CountryRepository countryRepository;

    public List<CountryDto> saveAndGetCountries(){
        log.info("ActionLog.saveAndGetCountries.start");

        List<CountryEntity> countries = countryRepository.findAll();
        if(countries.isEmpty()){
            CountriesnowBaseResp countriesnowBaseResp = countriesnowClient.getCountries();
            List<CountryResp> countryResps = countriesnowBaseResp.getData();
            List<CountryEntity> countryEntities = countryResps.stream().map(countryMapper::mapToEntity).toList();
            countryRepository.saveAll(countryEntities);

            List<CountryDto> countryDtos = countryEntities.stream().map(countryMapper::mapToDto).toList();
            log.info("ActionLog.saveAndGetCountries.end saved and returned");
            return countryDtos;
        }else {
            List<CountryDto> countryDtos = countries.stream().map(countryMapper::mapToDto).toList();
            log.info("ActionLog.saveAndGetCountries.end do not save just returned");
            return countryDtos;
        }

    }

}
