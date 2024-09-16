package com.example.iticketfinal.dto.event;

import com.example.iticketfinal.dto.image.ImageDto;
import com.example.iticketfinal.dto.performer.PerformerDto;
import com.example.iticketfinal.dto.ticket.TicketDto;
import com.example.iticketfinal.enums.EventCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
    private EventCategory category;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime eventDate;
    @NotNull
    private List<TicketDto> tickets;
    @NotBlank
    private Integer companyId;
    @NotBlank
    private Integer placeId;
    private List<ImageDto> images;
    private List<PerformerDto> performers;
}
