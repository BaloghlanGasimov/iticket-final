package com.example.iticketfinal.controller;

import com.example.iticketfinal.dto.BaseResponseDto;
import com.example.iticketfinal.dto.about.AboutDto;
import com.example.iticketfinal.service.AboutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/about")
@RequiredArgsConstructor
public class AboutController {
    private final AboutService aboutService;

    @PostMapping
    public void saveAbout(@RequestBody @Valid AboutDto aboutDto) {
        aboutService.saveAbout(aboutDto);
    }

    @GetMapping
    public BaseResponseDto<List<AboutDto>> getAbouts() {
        return aboutService.getAbouts();
    }

    @GetMapping("/{id}")
    public BaseResponseDto<AboutDto> getAboutById(@PathVariable Long id) {
        return aboutService.getAboutById(id);
    }

    @DeleteMapping("/{id}")
    public BaseResponseDto<AboutDto> deleteAbout(@PathVariable Long id) {
        return aboutService.deleteAbout(id);
    }

    @PutMapping("/{id}")
    public BaseResponseDto<AboutDto> editAbout(
            @PathVariable Long id,
            @RequestBody AboutDto aboutDto
    ) {
        return aboutService.editAbout(id, aboutDto);
    }
}
