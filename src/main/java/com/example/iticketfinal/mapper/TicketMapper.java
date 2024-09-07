package com.example.iticketfinal.mapper;

import com.example.iticketfinal.dao.entity.TicketEntity;
import com.example.iticketfinal.dto.ticket.TicketDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TicketMapper {
    TicketEntity mapToEntity(TicketDto ticket);
    TicketDto mapToDto(TicketEntity ticket);
}
