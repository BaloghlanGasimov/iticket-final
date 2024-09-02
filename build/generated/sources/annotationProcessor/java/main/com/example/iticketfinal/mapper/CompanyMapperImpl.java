package com.example.iticketfinal.mapper;

import com.example.iticketfinal.dao.entity.CompanyEntity;
import com.example.iticketfinal.dto.company.PrimaryCompanyLoginReqDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-02T13:32:30+0400",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class CompanyMapperImpl implements CompanyMapper {

    @Override
    public CompanyEntity mapToEntity(PrimaryCompanyLoginReqDto companyLoginReqDto) {
        if ( companyLoginReqDto == null ) {
            return null;
        }

        CompanyEntity companyEntity = new CompanyEntity();

        companyEntity.setName( companyLoginReqDto.getName() );
        companyEntity.setEmail( companyLoginReqDto.getEmail() );
        companyEntity.setLogo( companyLoginReqDto.getLogo() );

        return companyEntity;
    }
}
