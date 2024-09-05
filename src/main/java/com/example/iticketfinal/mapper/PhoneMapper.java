package com.example.iticketfinal.mapper;

import com.example.iticketfinal.dao.entity.PhoneEntity;
import com.example.iticketfinal.dto.phone.PhoneReqDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhoneMapper {

    PhoneReqDto mapToReqDto(PhoneEntity phoneEntity);
    PhoneEntity mapToEntity(PhoneReqDto phoneReqDto);

}
