package com.example.iticketfinal.mapper;

import com.example.iticketfinal.dao.entity.PerformerEntity;
import com.example.iticketfinal.dto.performer.PerformerDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PerformerMapper {

    PerformerDto mapToDto(PerformerEntity performerEntity);
    PerformerEntity mapToEntity(PerformerDto performerDto);

}
