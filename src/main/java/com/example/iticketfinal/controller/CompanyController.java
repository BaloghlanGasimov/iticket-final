package com.example.iticketfinal.controller;

import com.example.iticketfinal.dto.company.CompanyRespDto;
import com.example.iticketfinal.dto.company.PrimaryCompanyLoginReqDto;
import com.example.iticketfinal.enums.Status;
import com.example.iticketfinal.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;


    @GetMapping()
    public List<CompanyRespDto> getCompanies() {
        return companyService.getCompanies();
    }

    @PostMapping()
    public void savePrimaryCompany(
            @ModelAttribute @Valid PrimaryCompanyLoginReqDto companyLoginReqDto
            ) {
        companyService.savePrimaryCompany(companyLoginReqDto);
    }

    @PatchMapping("/{id}")
    public void changeStatus(
            @PathVariable Long id,
            @RequestParam Status status
    ) {
        companyService.changeStatus(id,status);
    }

}
