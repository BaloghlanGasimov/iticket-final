package com.example.iticketfinal.service;

import com.example.iticketfinal.dao.entity.PlaceEntity;
import com.example.iticketfinal.dao.repository.PlaceRepository;
import com.example.iticketfinal.dto.BaseResponseDto;
import com.example.iticketfinal.dto.place.PlaceDto;
import com.example.iticketfinal.enums.Exceptions;
import com.example.iticketfinal.exceptions.NotFoundException;
import com.example.iticketfinal.mapper.PlaceMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;
    private final PlaceMapper placeMapper;

    public void createPlace(PlaceDto placeDto) {
        log.info("ActionLog.createPlace.start placeDto: {}", placeDto);

        PlaceEntity placeEntity = placeMapper.mapToEntity(placeDto);
        placeRepository.save(placeEntity);

        log.info("ActionLog.createPlace.end placeDto: {}", placeDto);
    }

    public BaseResponseDto<List<PlaceDto>> getPlaces() {
        log.info("ActionLog.getPlaces.start");

        List<PlaceEntity> placeEntities = placeRepository.findAll();
        List<PlaceDto> placeDtos = placeEntities.stream().map(placeMapper::mapToDto).toList();

        log.info("ActionLog.getPlaces.end");
        return BaseResponseDto.success(placeDtos);
    }

    public BaseResponseDto<PlaceDto> getPlaceById(Long id) {
        log.info("ActionLog.getPlaceById.start id: {}",id);

        PlaceEntity place = findPlace(id);
        PlaceDto placeDto = placeMapper.mapToDto(place);

        log.info("ActionLog.getPlaceById.end id: {}",id);
        return BaseResponseDto.success(placeDto);
    }

    public void updatePlace(Long id, PlaceDto placeDto) {
        log.info("ActionLog.updatePlace.start id: {},placeDto: {}",id, placeDto);

        PlaceEntity place = findPlace(id);
        place =placeMapper.mapUpdatingToEntity(place,placeDto);
        placeRepository.save(place);

        log.info("ActionLog.updatePlace.end id: {},placeDto: {}",id, placeDto);
    }

    public BaseResponseDto<PlaceDto> deletePlace(Long id) {
        log.info("ActionLog.deletePlace.start id: {}",id);

        PlaceEntity place = findPlace(id);
        PlaceDto placeDto = placeMapper.mapToDto(place);
        placeRepository.delete(place);

        log.info("ActionLog.deletePlace.end id: {}",id);
        return BaseResponseDto.success(placeDto);
    }

    private PlaceEntity findPlace(Long id) {
        PlaceEntity placeEntity = placeRepository.findById(id)
                .orElseThrow(()-> new NotFoundException(
                        Exceptions.PLACE_NOT_FOUND.toString(),
                        String.format("ActionLog.findPlace.error NotFound id: %d",id)
                ));
        return placeEntity;
    }

}
