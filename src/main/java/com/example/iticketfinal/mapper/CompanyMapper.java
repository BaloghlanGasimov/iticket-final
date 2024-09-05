package com.example.iticketfinal.mapper;

import com.example.iticketfinal.dao.entity.CompanyEntity;
import com.example.iticketfinal.dto.company.CompanyRespDto;
import com.example.iticketfinal.dto.company.CompanyPrimaryLoginReqDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyEntity mapToEntity(CompanyPrimaryLoginReqDto companyLoginReqDto);
    CompanyRespDto mapToRespDto(CompanyEntity companyEntity);

}
