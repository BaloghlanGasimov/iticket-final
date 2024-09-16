package com.example.iticketfinal.mapper;

import com.example.iticketfinal.client.model.CountryResp;
import com.example.iticketfinal.dao.entity.*;
import com.example.iticketfinal.dto.about.AboutDto;
import com.example.iticketfinal.dto.company.CompanyPrimaryLoginReqDto;
import com.example.iticketfinal.dto.company.CompanyRespDto;
import com.example.iticketfinal.dto.country.CountryDto;
import com.example.iticketfinal.dto.event.EventReqDto;
import com.example.iticketfinal.dto.event.EventRespDto;
import com.example.iticketfinal.dto.image.ImageDto;
import com.example.iticketfinal.dto.performer.PerformerDto;
import com.example.iticketfinal.dto.phone.PhoneReqDto;
import com.example.iticketfinal.dto.place.PlaceDto;
import com.example.iticketfinal.dto.ticket.TicketDto;
import com.example.iticketfinal.dto.user.UserPrimaryLoginReqDto;
import com.example.iticketfinal.dto.user.UserRespDto;
import org.mapstruct.Mapper;

@Mapper
public interface CommonMapper {

    AboutDto mapToDto(AboutEntity aboutEntity);

    AboutEntity mapToEntity(AboutDto aboutDto);

    CompanyEntity mapToEntity(CompanyPrimaryLoginReqDto companyLoginReqDto);

    CompanyRespDto mapToRespDto(CompanyEntity companyEntity);

    CountryEntity mapToEntity(CountryDto countryDto);

    CountryEntity mapToEntity(CountryResp countryResp);

    CountryDto mapToDto(CountryEntity countryEntity);

    CountryDto mapToDto(CountryResp countryResp);

    EventReqDto mapToReqDto(EventEntity eventEntity);

    EventEntity mapToEntity(EventReqDto eventReqDto);

    EventRespDto mapToRespDto(EventEntity eventEntity);

    ImageDto mapToDto(ImageEntity imageEntity);

    ImageEntity mapToEntity(ImageDto imageDto);

    PerformerDto mapToDto(PerformerEntity performerEntity);

    PerformerEntity mapToEntity(PerformerDto performerDto);

    PhoneReqDto mapToReqDto(PhoneEntity phoneEntity);

    PhoneEntity mapToEntity(PhoneReqDto phoneReqDto);

    PlaceEntity mapToEntity(PlaceDto placeDto);

    PlaceDto mapToDto(PlaceEntity placeEntity);

    default PlaceEntity mapUpdatingToEntity(PlaceEntity place, PlaceDto placeDto) {

        if (placeDto.getName() != null) {
            place.setName(placeDto.getName());
        }
        if (placeDto.getAddress() != null) {
            place.setAddress(placeDto.getAddress());
        }
        if (placeDto.getLocation() != null) {
            place.setLocation(placeDto.getLocation());
        }

        return place;
    }

    TicketEntity mapToEntity(TicketDto ticket);

    TicketDto mapToDto(TicketEntity ticket);

    UserEntity mapToEntity(UserPrimaryLoginReqDto userPrimaryLoginReqDto);

    UserRespDto mapToRespDto(UserEntity userEntity);

}
