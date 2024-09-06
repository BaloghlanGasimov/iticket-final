package com.example.iticketfinal.service;

import com.example.iticketfinal.dao.entity.EventEntity;
import com.example.iticketfinal.dto.event.EventReqDto;
import com.sun.jdi.request.EventRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventService {

    public void saveEvent(EventReqDto eventReqDto) {
        log.info("ActionLog.saveEvent.start eventReqDto: {}", eventReqDto);

//        EventEntity eventEntity = ;

        log.info("ActionLog.saveEvent.end eventReqDto: {}", eventReqDto);
    }

}
