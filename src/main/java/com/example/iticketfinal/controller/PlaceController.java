package com.example.iticketfinal.controller;

import com.example.iticketfinal.dto.BaseResponseDto;
import com.example.iticketfinal.dto.place.PlaceDto;
import com.example.iticketfinal.service.PlaceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/places")
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;

    @PostMapping
    public void createPlace(
            @RequestBody @Valid PlaceDto placeDto
    ) {
        placeService.createPlace(placeDto);
    }

    @GetMapping
    public BaseResponseDto<List<PlaceDto>> getPlaces() {
        return placeService.getPlaces();
    }

    @GetMapping("/{id}")
    public BaseResponseDto<PlaceDto> getPlaceById(
            @PathVariable Long id
    ) {
        return placeService.getPlaceById(id);
    }

    @PutMapping("/{id}")
    public void updatePlace(
            @PathVariable Long id,
            @RequestBody @Valid PlaceDto placeDto
    ) {
        placeService.updatePlace(id, placeDto);
    }

    @DeleteMapping("/{id}")
    public BaseResponseDto<PlaceDto> deletePlace(
            @PathVariable Long id
    ) {
        return placeService.deletePlace(id);
    }
}
