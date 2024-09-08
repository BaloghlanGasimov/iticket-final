package com.example.iticketfinal.service;

import com.example.iticketfinal.dao.entity.EventEntity;
import com.example.iticketfinal.dao.entity.ImageEntity;
import com.example.iticketfinal.dto.event.EventReqDto;
import com.example.iticketfinal.mapper.EventMapper;
import com.sun.jdi.request.EventRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {
    private final EventMapper eventMapper;
    private final ImageService imageService;

    public void saveEvent(EventReqDto eventReqDto) {
        log.info("ActionLog.saveEvent.start eventReqDto: {}", eventReqDto);

        EventEntity eventEntity = eventMapper.mapToEntity(eventReqDto);
        List<ImageEntity> images = new ArrayList<>();
        for (int i = 0; i <eventReqDto.getEventImages().size() ; i++) {
            try {
//                ImageEntity image = imageService.setImageToBucket(eventReqDto.getEventImages().get(i),"event");
//                images.add(image);
            }catch (Exception e){
                e.printStackTrace();
                log.error("ActionLog.saveEvent.error when setting imageToMinioBucket and getting imageEntity {}",eventReqDto);
            }
        }
        eventEntity.setImages(images);
        log.info("ActionLog.saveEvent.end eventReqDto: {}", eventReqDto);
    }

}
