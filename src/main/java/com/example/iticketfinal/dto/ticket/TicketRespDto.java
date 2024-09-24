package com.example.iticketfinal.dto.ticket;

import com.example.iticketfinal.dto.company.CompanyRespDto;
import com.example.iticketfinal.dto.place.PlaceDto;
import com.example.iticketfinal.enums.TicketCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketRespDto {
    private TicketCategory category;
    private Integer count;
    private Double price;
    private EventDto event;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class EventDto{
        private Long id;
        private String title;
        private String description;
        private LocalDateTime eventDate;
        private CompanyRespDto company;
        private PlaceDto place;
    }
}
