package com.example.iticketfinal.controller;

import com.example.iticketfinal.dto.BaseResponseDto;
import com.example.iticketfinal.dto.event.EventReqDto;
import com.example.iticketfinal.dto.event.EventRespDto;
import com.example.iticketfinal.service.EventService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    public void saveEvent(
            @RequestBody EventReqDto eventReqDto
    ){
        eventService.saveEvent(eventReqDto);
    }

    @PutMapping("/{id}")
    public void editEvent(
            @PathVariable Long id,
            @RequestBody EventReqDto eventReqDto
    ){
        eventService.editEvent(id,eventReqDto);
    }

    @DeleteMapping("/{id}")
    public BaseResponseDto<EventRespDto> deleteEvent(
            @PathVariable Long id
    ){
        return eventService.deleteEvent(id);
    }

    @GetMapping
    public BaseResponseDto<List<EventRespDto>> getAllEvents(
            @RequestParam(required = false) Boolean expired
    ){
        return eventService.getAllEvents(expired);
    }

    @GetMapping("/{id}")
    public BaseResponseDto<EventRespDto> getEventById(
            @PathVariable Long id
    ){
        return eventService.getEventById(id);
    }
}
