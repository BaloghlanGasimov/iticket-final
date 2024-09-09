package com.example.iticketfinal.mapper;

import com.example.iticketfinal.dao.entity.ImageEntity;
import com.example.iticketfinal.dto.image.ImageDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ImageMapper {
    ImageDto mapToDto(ImageEntity imageEntity);
    ImageEntity mapToEntity(ImageDto imageDto);
}
