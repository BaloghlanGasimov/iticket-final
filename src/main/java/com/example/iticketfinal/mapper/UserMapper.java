package com.example.iticketfinal.mapper;

import com.example.iticketfinal.dao.entity.CountryEntity;
import com.example.iticketfinal.dao.entity.UserEntity;
import com.example.iticketfinal.dto.user.UserLoginReqDto;
import com.example.iticketfinal.dto.user.UserPrimaryLoginReqDto;
import com.example.iticketfinal.dto.user.UserRespDto;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity mapToEntity(UserPrimaryLoginReqDto userPrimaryLoginReqDto);
    UserRespDto mapToRespDto(UserEntity userEntity);

    default UserEntity mapUpdatingToEntity(UserEntity userEntity,UserLoginReqDto userLoginReqDto){

        if(userLoginReqDto.getName()!=null){
            userEntity.setName(userLoginReqDto.getName());
        }
        if(userLoginReqDto.getSurname()!=null){
            userEntity.setSurname(userLoginReqDto.getSurname());
        }
        if(userLoginReqDto.getEmail()!=null){
            userEntity.setEmail(userLoginReqDto.getEmail());
        }
        if(userLoginReqDto.getPhone()!=null){
            userEntity.setPhone(userLoginReqDto.getPhone());
        }
        if(userLoginReqDto.getBirthDate()!=null){
            userEntity.setBirthDate(userLoginReqDto.getBirthDate());
        }
        if(userLoginReqDto.getGender()!=null){
            userEntity.setGender(userLoginReqDto.getGender());
        }
        if(userLoginReqDto.getCountry()!=null){
            CountryEntity countryEntity =new CountryEntity();
            if(userLoginReqDto.getCountry().getName()!=null){
                countryEntity.setName(userLoginReqDto.getCountry().getName());
            }
            userEntity.setCountry(countryEntity);
        }

        return userEntity;
    }

}
