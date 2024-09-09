package com.example.iticketfinal.mapper;

import com.example.iticketfinal.client.model.CountryResp;
import com.example.iticketfinal.dao.entity.CountryEntity;
import com.example.iticketfinal.dto.country.CountryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryEntity mapToEntity(CountryDto countryDto);
    CountryEntity mapToEntity(CountryResp countryResp);
    CountryDto mapToDto(CountryEntity countryEntity);
    CountryDto mapToDto(CountryResp countryResp);

}
