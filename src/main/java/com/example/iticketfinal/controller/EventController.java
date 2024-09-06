package com.example.iticketfinal.controller;

import com.example.iticketfinal.dto.BaseResponseDto;
import com.example.iticketfinal.dto.event.EventReqDto;
import com.example.iticketfinal.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @PostMapping
    public void saveEvent(
            @ModelAttribute EventReqDto eventReqDto
    ){
        eventService.saveEvent(eventReqDto);
    }

}
