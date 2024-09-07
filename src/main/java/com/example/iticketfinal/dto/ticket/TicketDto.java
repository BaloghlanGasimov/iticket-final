package com.example.iticketfinal.dto.ticket;

import com.example.iticketfinal.enums.TicketCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketDto {
    private TicketCategory category;
    private Integer count;
    private Double price;
}
