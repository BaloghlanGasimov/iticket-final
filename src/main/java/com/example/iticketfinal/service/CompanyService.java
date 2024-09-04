package com.example.iticketfinal.service;

import com.example.iticketfinal.dao.entity.CompanyEntity;
import com.example.iticketfinal.dao.repository.CompanyRepository;
import com.example.iticketfinal.dto.company.CompanyRespDto;
import com.example.iticketfinal.dto.company.PrimaryCompanyLoginReqDto;
import com.example.iticketfinal.enums.Exceptions;
import com.example.iticketfinal.enums.Status;
import com.example.iticketfinal.exceptions.NotFoundException;
import com.example.iticketfinal.mapper.CompanyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final ImageService imageService;

    public List<CompanyRespDto> getCompanies(){
        log.info("ActionLog.getCompanies.start");

        List<CompanyEntity> companyEntities = companyRepository.findAll();
        List<CompanyRespDto> companyRespDtos = companyEntities.stream().map(companyMapper::mapToRespDto).toList();

        log.info("ActionLog.getCompanies.end");
        return companyRespDtos;
    }
    public PrimaryCompanyLoginReqDto savePrimaryCompany(@RequestBody PrimaryCompanyLoginReqDto companyLoginReqDto){
        log.info("ActionLog.savePrimaryCompany.start {}", companyLoginReqDto);

        CompanyEntity companyEntity = companyMapper.mapToEntity(companyLoginReqDto);
        try {
            companyEntity.setLogo(imageService.setImageToBucket(companyLoginReqDto.getLogoImage(),"company"));
        }catch (Exception e){
            e.printStackTrace();
            log.error("ActionLog.savePrimaryCompany.error when setting imageToMinioBucket and getting String {}",companyLoginReqDto);
        }
        companyRepository.save(companyEntity);

        log.info("ActionLog.savePrimaryCompany.end {}", companyLoginReqDto);
        return companyLoginReqDto;
    }

    public void changeStatus(Long id,Status status){
        log.info("ActionLog.changeStatus.start id: {}, status: {}",id,status);

        CompanyEntity company = findCompany(id);
        company.setStatus(status);
        companyRepository.save(company);

        log.info("ActionLog.changeStatus.end id: {}, status: {}",id,status);
    }

    private CompanyEntity findCompany(Long id){
        CompanyEntity company = companyRepository.findById(id)
                .orElseThrow(()->new NotFoundException(
                        Exceptions.COMPANY_NOT_FOUND.toString(),
                        String.format("ActionLog.findCompany.error NotFound %d",id)
                ));
        return company;
    }

}
