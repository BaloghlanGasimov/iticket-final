package com.example.iticketfinal.mapper;

import com.example.iticketfinal.dao.entity.CountryEntity;
import com.example.iticketfinal.dto.country.CountryDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    CountryEntity mapToEntity(CountryDto countryDto);
}
