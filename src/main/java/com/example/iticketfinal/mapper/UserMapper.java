package com.example.iticketfinal.mapper;

import com.example.iticketfinal.dao.entity.UserEntity;
import com.example.iticketfinal.dto.user.UserPrimaryLoginReqDto;
import com.example.iticketfinal.dto.user.UserRespDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity mapToEntity(UserPrimaryLoginReqDto userPrimaryLoginReqDto);
    UserRespDto mapToRespDto(UserEntity userEntity);
}
