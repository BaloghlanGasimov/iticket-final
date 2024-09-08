//package com.example.iticketfinal.service;
//
//import com.example.iticketfinal.dao.entity.CompanyEntity;
//import com.example.iticketfinal.dao.entity.EventEntity;
//import com.example.iticketfinal.dao.entity.PhoneEntity;
//import com.example.iticketfinal.dao.repository.CompanyRepository;
//import com.example.iticketfinal.dto.BaseResponseDto;
//import com.example.iticketfinal.dto.company.CompanyLoginReqDto;
//import com.example.iticketfinal.dto.company.CompanyPrimaryLoginReqDto;
//import com.example.iticketfinal.dto.company.CompanyRespDto;
//import com.example.iticketfinal.enums.Exceptions;
//import com.example.iticketfinal.enums.Status;
//import com.example.iticketfinal.exceptions.NotFoundException;
//import com.example.iticketfinal.mapper.CompanyMapper;
//import com.example.iticketfinal.mapper.EventMapper;
//import com.example.iticketfinal.mapper.PhoneMapper;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import java.util.List;
//
//@Service
//@Slf4j
//@RequiredArgsConstructor
//public class CompanyService {
//    private final CompanyRepository companyRepository;
//    private final CompanyMapper companyMapper;
//    private final ImageService imageService;
//    private final PhoneMapper phoneMapper;
//    private final EventMapper eventMapper;
//
//    public BaseResponseDto<CompanyRespDto> deleteCompany(Long id) {
//        log.info("ActionLog.deleteCompany.start id: {}",id);
//
//        CompanyEntity companyEntity = findCompany(id);
//        CompanyRespDto companyRespDto = companyMapper.mapToRespDto(companyEntity);
//        companyRepository.deleteById(id);
//
//        log.info("ActionLog.deleteCompany.end id: {}",id);
//        return BaseResponseDto.success(companyRespDto);
//    }
//
//    public BaseResponseDto<CompanyRespDto> getCompanyById(Long id){
//        log.info("ActionLog.getCompanyById.start id: {}",id);
//
//        CompanyEntity companyEntity = findCompany(id);
//        CompanyRespDto companyRespDto = companyMapper.mapToRespDto(companyEntity);
//
//        log.info("ActionLog.getCompanyById.end id: {}",id);
//        return BaseResponseDto.success(companyRespDto);
//    }
//
//    public BaseResponseDto<List<CompanyRespDto>> getCompanies(Status status){
//        log.info("ActionLog.getCompanies.start");
//
//        List<CompanyEntity> companyEntities = companyRepository.findAll();
//        List<CompanyRespDto> companyRespDtos = companyEntities.stream().map(companyMapper::mapToRespDto).toList();
//        companyRespDtos = companyRespDtos.stream().filter(
//                company -> status==null || company.getStatus().equals(status)
//        ).toList();
//
//        log.info("ActionLog.getCompanies.end");
//        return BaseResponseDto.success(companyRespDtos);
//    }
//    public BaseResponseDto<CompanyRespDto> savePrimaryCompany(CompanyPrimaryLoginReqDto companyLoginReqDto){
//        log.info("ActionLog.savePrimaryCompany.start {}", companyLoginReqDto);
//
//        CompanyEntity companyEntity = companyMapper.mapToEntity(companyLoginReqDto);
//        companyRepository.save(companyEntity);
//        CompanyRespDto companyRespDto = companyMapper.mapToRespDto(companyEntity);
//
//        log.info("ActionLog.savePrimaryCompany.end {}", companyLoginReqDto);
//        return BaseResponseDto.success(companyRespDto);
//    }
//
//    public void changeStatus(Long id,Status status){
//        log.info("ActionLog.changeStatus.start id: {}, status: {}",id,status);
//
//        CompanyEntity company = findCompany(id);
//        company.setStatus(status);
//        companyRepository.save(company);
//
//        log.info("ActionLog.changeStatus.end id: {}, status: {}",id,status);
//    }
//
//    @Transactional
//    public CompanyRespDto updateCompany(Long id, CompanyLoginReqDto companyLoginReqDto){
//        log.info("ActionLog.updateCompany.start id: {}, companyLoginReqDto: {}",id,companyLoginReqDto);
//
//        CompanyEntity company = findCompany(id);
//
//        if(companyLoginReqDto.getName()!=null){
//            company.setName(companyLoginReqDto.getName());
//        }
//        if(companyLoginReqDto.getEmail()!=null){
//            company.setEmail(companyLoginReqDto.getEmail());
//        }
//        if(companyLoginReqDto.getWebsite()!=null){
//            company.setWebsite(companyLoginReqDto.getWebsite());
//        }
//        if(companyLoginReqDto.getDescription()!=null){
//            company.setDescription(companyLoginReqDto.getDescription());
//        }
//        if(companyLoginReqDto.getPhones()!=null){
//            List<PhoneEntity> phones = companyLoginReqDto.getPhones().stream().map(phoneMapper::mapToEntity).toList();
//            company.setPhones(phones);
//        }
//        if(companyLoginReqDto.getLogoImage()!=null){
//            imageService.deleteFile(company.getLogo().getName(),company.getLogo().getBucket());
//            try {
////                company.setLogo(imageService.setImageToBucket(companyLoginReqDto.getLogoImage(),"company"));
//            } catch (Exception e) {
//                e.printStackTrace();
//                throw new RuntimeException(e.getMessage());
//            }
//        }
//        CompanyRespDto companyRespDto = companyMapper.mapToRespDto(company);
//
//        log.info("ActionLog.updateCompany.end id: {}, companyLoginReqDto: {}",id,companyLoginReqDto);
//        return companyRespDto;
//    }
//
//    private CompanyEntity findCompany(Long id){
//        CompanyEntity company = companyRepository.findById(id)
//                .orElseThrow(()->new NotFoundException(
//                        Exceptions.COMPANY_NOT_FOUND.toString(),
//                        String.format("ActionLog.findCompany.error NotFound %d",id)
//                ));
//        return company;
//    }
//
//}

///*
package com.example.iticketfinal.service;

import com.example.iticketfinal.dao.entity.CompanyEntity;
import com.example.iticketfinal.dao.entity.EventEntity;
import com.example.iticketfinal.dao.entity.ImageEntity;
import com.example.iticketfinal.dao.entity.PhoneEntity;
import com.example.iticketfinal.dao.repository.CompanyRepository;
import com.example.iticketfinal.dto.BaseResponseDto;
import com.example.iticketfinal.dto.company.CompanyLoginReqDto;
import com.example.iticketfinal.dto.company.CompanyPrimaryLoginReqDto;
import com.example.iticketfinal.dto.company.CompanyRespDto;
import com.example.iticketfinal.enums.Exceptions;
import com.example.iticketfinal.enums.Status;
import com.example.iticketfinal.exceptions.NotFoundException;
import com.example.iticketfinal.mapper.CompanyMapper;
import com.example.iticketfinal.mapper.EventMapper;
import com.example.iticketfinal.mapper.ImageMapper;
import com.example.iticketfinal.mapper.PhoneMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final ImageService imageService;
    private final ImageMapper imageMapper;
    private final PhoneMapper phoneMapper;
    private final EventMapper eventMapper;

    public BaseResponseDto<CompanyRespDto> deleteCompany(Long id) {
        log.info("ActionLog.deleteCompany.start id: {}",id);

        CompanyEntity companyEntity = findCompany(id);
        CompanyRespDto companyRespDto = companyMapper.mapToRespDto(companyEntity);
        companyRepository.deleteById(id);

        log.info("ActionLog.deleteCompany.end id: {}",id);
        return BaseResponseDto.success(companyRespDto);
    }

    public BaseResponseDto<CompanyRespDto> getCompanyById(Long id){
        log.info("ActionLog.getCompanyById.start id: {}",id);

        CompanyEntity companyEntity = findCompany(id);
        CompanyRespDto companyRespDto = companyMapper.mapToRespDto(companyEntity);

        log.info("ActionLog.getCompanyById.end id: {}",id);
        return BaseResponseDto.success(companyRespDto);
    }

    public BaseResponseDto<List<CompanyRespDto>> getCompanies(Status status){
        log.info("ActionLog.getCompanies.start");

        List<CompanyEntity> companyEntities = companyRepository.findAll();
        List<CompanyRespDto> companyRespDtos = companyEntities.stream().map(companyMapper::mapToRespDto).toList();
        companyRespDtos = companyRespDtos.stream().filter(
                company -> status==null || company.getStatus().equals(status)
        ).toList();

        log.info("ActionLog.getCompanies.end");
        return BaseResponseDto.success(companyRespDtos);
    }
    public BaseResponseDto<CompanyRespDto> savePrimaryCompany(CompanyPrimaryLoginReqDto companyLoginReqDto){
        log.info("ActionLog.savePrimaryCompany.start {}", companyLoginReqDto);

        CompanyEntity companyEntity = companyMapper.mapToEntity(companyLoginReqDto);
        companyRepository.save(companyEntity);
        CompanyRespDto companyRespDto = companyMapper.mapToRespDto(companyEntity);

        log.info("ActionLog.savePrimaryCompany.end {}", companyLoginReqDto);
        return BaseResponseDto.success(companyRespDto);
    }

    public void changeStatus(Long id,Status status){
        log.info("ActionLog.changeStatus.start id: {}, status: {}",id,status);

        CompanyEntity company = findCompany(id);
        company.setStatus(status);
        companyRepository.save(company);

        log.info("ActionLog.changeStatus.end id: {}, status: {}",id,status);
    }

    @Transactional
    public CompanyRespDto updateCompany(Long id, CompanyLoginReqDto companyLoginReqDto){
        log.info("ActionLog.updateCompany.start id: {}, companyLoginReqDto: {}",id,companyLoginReqDto);

        CompanyEntity company = findCompany(id);

        if(companyLoginReqDto.getName()!=null){
            company.setName(companyLoginReqDto.getName());
        }
        if(companyLoginReqDto.getEmail()!=null){
            company.setEmail(companyLoginReqDto.getEmail());
        }
        if(companyLoginReqDto.getWebsite()!=null){
            company.setWebsite(companyLoginReqDto.getWebsite());
        }
        if(companyLoginReqDto.getDescription()!=null){
            company.setDescription(companyLoginReqDto.getDescription());
        }
        if(companyLoginReqDto.getPhones()!=null){
            List<PhoneEntity> phones = companyLoginReqDto.getPhones().stream().map(phoneMapper::mapToEntity).toList();
            company.setPhones(phones);
        }
        if(companyLoginReqDto.getLogo()!=null){
            imageService.deleteFile(company.getLogo().getBucket(),company.getLogo().getName());
            try {
                ImageEntity imageEntity = imageMapper.mapToEntity(companyLoginReqDto.getLogo());
                company.setLogo(imageEntity);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }
        CompanyRespDto companyRespDto = companyMapper.mapToRespDto(company);

        log.info("ActionLog.updateCompany.end id: {}, companyLoginReqDto: {}",id,companyLoginReqDto);
        return companyRespDto;
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

// */