package com.example.iticketfinal.service;

import com.example.iticketfinal.dao.entity.*;
import com.example.iticketfinal.dao.repository.CompanyRepository;
import com.example.iticketfinal.dao.repository.EventRepository;
import com.example.iticketfinal.dao.repository.PerformerRepository;
import com.example.iticketfinal.dao.repository.PlaceRepository;
import com.example.iticketfinal.dto.BaseResponseDto;
import com.example.iticketfinal.dto.event.EventReqDto;
import com.example.iticketfinal.dto.event.EventRespDto;
import com.example.iticketfinal.enums.Exceptions;
import com.example.iticketfinal.exceptions.NotFoundException;
import com.example.iticketfinal.mapper.EventMapper;
import com.sun.jdi.request.EventRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {
    private final CompanyRepository companyRepository;
    private final PlaceRepository placeRepository;
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final PerformerRepository performerRepository;

    public void checkEventExpire(){
        log.info("ActionLog.checkEventExpire.start");

        List<EventEntity> eventEntities = eventRepository.findAllByExpiredIsFalse();
        List<EventEntity> expiredEvents = eventEntities.stream().filter((event)->event.getEventDate().isBefore(LocalDateTime.now())).toList();
        expiredEvents.stream().forEach(expiredEvent -> expiredEvent.setExpired(true));
        eventRepository.saveAll(expiredEvents);

        log.info("ActionLog.checkEventExpire.end");
    }

    public void saveEvent(EventReqDto eventReqDto) {
        log.info("ActionLog.saveEvent.start eventReqDto: {}", eventReqDto);

        EventEntity eventEntity = eventMapper.mapToEntity(eventReqDto);
        PlaceEntity place = placeRepository.findById((long)eventReqDto.getPlaceId()).orElse(null);
        CompanyEntity company = companyRepository.findById((long)eventReqDto.getCompanyId()).orElse(null);
        eventEntity.setPlace(place);
        eventEntity.setCompany(company);
        if(eventEntity.getTickets()!=null){
            for(TicketEntity ticketEntity : eventEntity.getTickets()){
                ticketEntity.setEvent(eventEntity);
            }
        }
        List<PerformerEntity> performerEntities = eventEntity.getPerformers();
        if(performerEntities!=null){
            for(int i=0; i< performerEntities.size();i++){
                String username = (performerEntities.get(i).getName()+"_"+performerEntities.get(i).getSurname()).toLowerCase();
                performerEntities.get(i).setUsername(username);
                PerformerEntity performer =  performerRepository.findByUsername(username).orElse(null);
                if(performer!=null){
                    performerEntities.set(i,performer);
                }
            }
        }
        eventEntity.setPerformers(performerEntities);
        eventRepository.save(eventEntity);
        log.info("ActionLog.saveEvent.end eventReqDto: {}", eventReqDto);
    }

    public BaseResponseDto<EventRespDto> deleteEvent(Long id){
        log.info("ActionLog.deleteEvent.start id: {}", id);

        EventEntity eventEntity = findEvent(id);
        EventRespDto eventRespDto = eventMapper.mapToRespDto(eventEntity);
        eventRepository.delete(eventEntity);

        log.info("ActionLog.saveEvent.end id: {}", id);
        return BaseResponseDto.success(eventRespDto);
    }

    private EventEntity findEvent(Long id){
        EventEntity event = eventRepository.findById(id)
                .orElseThrow(()->new NotFoundException(
                        Exceptions.EVENT_NOT_FOUND.toString(),
                        String.format("ActionLog.findEvent.error NotFound Event %d",id)
                ));
        return event;
    }

}
