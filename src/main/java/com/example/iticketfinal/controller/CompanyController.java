package com.example.iticketfinal.controller;

import com.example.iticketfinal.dto.company.PrimaryCompanyLoginReqDto;
import com.example.iticketfinal.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping()
    public PrimaryCompanyLoginReqDto savePrimaryCompany(@RequestBody PrimaryCompanyLoginReqDto companyLoginReqDto) {
        return companyService.savePrimaryCompany(companyLoginReqDto);
    }

}
