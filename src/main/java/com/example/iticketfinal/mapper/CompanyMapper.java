package com.example.iticketfinal.mapper;

import com.example.iticketfinal.dao.entity.CompanyEntity;
import com.example.iticketfinal.dto.company.PrimaryCompanyLoginReqDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    CompanyEntity mapToEntity(PrimaryCompanyLoginReqDto companyLoginReqDto);

}
