package com.example.iticketfinal.mapper;

import com.example.iticketfinal.dao.entity.CountryEntity;
import com.example.iticketfinal.dao.entity.PlaceEntity;
import com.example.iticketfinal.dao.entity.UserEntity;
import com.example.iticketfinal.dto.place.PlaceDto;
import com.example.iticketfinal.dto.user.UserLoginReqDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlaceMapper {
    PlaceEntity mapToEntity(PlaceDto placeDto);
    PlaceDto mapToDto(PlaceEntity placeEntity);

    default PlaceEntity mapUpdatingToEntity(PlaceEntity place, PlaceDto placeDto){

        if(placeDto.getName()!=null){
            place.setName(placeDto.getName());
        }
        if(placeDto.getAddress()!=null){
            place.setAddress(placeDto.getAddress());
        }
        if(placeDto.getLocation()!=null){
            place.setLocation(placeDto.getLocation());
        }

        return place;
    }
}
