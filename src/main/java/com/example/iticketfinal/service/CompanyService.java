package com.example.iticketfinal.service;

import com.example.iticketfinal.dao.entity.CompanyEntity;
import com.example.iticketfinal.dao.repository.CompanyRepository;
import com.example.iticketfinal.dto.company.PrimaryCompanyLoginReqDto;
import com.example.iticketfinal.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public PrimaryCompanyLoginReqDto savePrimaryCompany(@RequestBody PrimaryCompanyLoginReqDto companyLoginReqDto){
        log.info("ActionLog.savePrimaryCompany.start {}", companyLoginReqDto);

        CompanyEntity companyEntity = companyMapper.mapToEntity(companyLoginReqDto);
        companyRepository.save(companyEntity);

        log.info("ActionLog.savePrimaryCompany.end {}", companyLoginReqDto);
        return companyLoginReqDto;
    }

}
