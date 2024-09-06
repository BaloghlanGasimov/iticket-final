package com.example.iticketfinal.dto.event;

import com.example.iticketfinal.dao.entity.TicketEntity;
import com.example.iticketfinal.dto.ticket.TicketDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventReqDto {

    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotNull
    private LocalDateTime eventDate;
    @NotNull
    private List<TicketDto> tickets;
}
