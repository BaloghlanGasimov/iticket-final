package com.example.iticketfinal.mapper;

import com.example.iticketfinal.dao.entity.EventEntity;
import com.example.iticketfinal.dao.entity.PhoneEntity;
import com.example.iticketfinal.dto.event.EventReqDto;
import com.example.iticketfinal.dto.event.EventRespDto;
import com.example.iticketfinal.dto.phone.PhoneReqDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventMapper {

    EventReqDto mapToReqDto(EventEntity eventEntity);
    EventEntity mapToEntity(EventReqDto eventReqDto);
    EventRespDto mapToRespDto(EventEntity eventEntity);

}
