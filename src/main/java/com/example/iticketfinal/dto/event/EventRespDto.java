package com.example.iticketfinal.dto.event;

import com.example.iticketfinal.dto.company.CompanyRespDto;
import com.example.iticketfinal.dto.image.ImageDto;
import com.example.iticketfinal.dto.performer.PerformerDto;
import com.example.iticketfinal.dto.place.PlaceDto;
import com.example.iticketfinal.dto.ticket.TicketDto;
import com.example.iticketfinal.enums.EventCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRespDto {
    private Long id;
    private String title;
    private String description;
    private EventCategory category;
    private LocalDateTime eventDate;
    private List<TicketDto> tickets;
    private CompanyRespDto company;
    private PlaceDto place;
    private List<ImageDto> images;
    private List<PerformerDto> performers;
}
