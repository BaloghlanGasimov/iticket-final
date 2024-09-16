package com.example.iticketfinal.mapper;

import com.example.iticketfinal.dao.entity.AboutEntity;
import com.example.iticketfinal.dto.about.AboutDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AboutMapper {

    AboutDto mapToDto(AboutEntity aboutEntity);
    AboutEntity mapToEntity(AboutDto aboutDto);

}
