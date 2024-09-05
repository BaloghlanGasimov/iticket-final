package com.example.iticketfinal.controller;

import com.example.iticketfinal.dto.BaseResponseDto;
import com.example.iticketfinal.dto.company.CompanyLoginReqDto;
import com.example.iticketfinal.dto.company.CompanyRespDto;
import com.example.iticketfinal.dto.company.PrimaryCompanyLoginReqDto;
import com.example.iticketfinal.enums.Status;
import com.example.iticketfinal.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;


    @GetMapping()
    public BaseResponseDto<List<CompanyRespDto>> getCompanies(
            @RequestParam(required = false) Status status
    ) {
        return companyService.getCompanies(status);
    }

    @GetMapping("/{id}")
    public BaseResponseDto<CompanyRespDto> getCompanies(
            @PathVariable Long id
    ) {

        return companyService.getCompanyById(id);
    }

    @PostMapping("/primary")
    public BaseResponseDto<CompanyRespDto> savePrimaryCompany(
            @ModelAttribute @Valid PrimaryCompanyLoginReqDto companyLoginReqDto
            ) {
        return companyService.savePrimaryCompany(companyLoginReqDto);
    }

    @PatchMapping("/{id}")
    public void changeStatus(
            @PathVariable Long id,
            @RequestParam Status status
    ) {
        companyService.changeStatus(id,status);
    }

    @PutMapping("/{id}")
    public CompanyRespDto updateCompany(
            @PathVariable Long id,
            @Valid @ModelAttribute CompanyLoginReqDto companyLoginReqDto
            ){
        return companyService.updateCompany(id,companyLoginReqDto);
    }

    @DeleteMapping("/{id}")
    public BaseResponseDto<CompanyRespDto> deleteCompany(
            @PathVariable Long id
    ){
        return companyService.deleteCompany(id);
    }

}
