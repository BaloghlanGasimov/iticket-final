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
import com.example.iticketfinal.mapper.PerformerMapper;
import com.example.iticketfinal.mapper.TicketMapper;
import com.sun.jdi.request.EventRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {
    private final CompanyRepository companyRepository;
    private final PlaceRepository placeRepository;
    private final EventRepository eventRepository;
    private final EventMapper eventMapper;
    private final PerformerRepository performerRepository;
    private final TicketMapper ticketMapper;
    private final PerformerMapper performerMapper;

    public void checkEventExpire(){
        log.info("ActionLog.checkEventExpire.start");

        List<EventEntity> eventEntities = eventRepository.findAllByExpiredIsFalse();
        List<EventEntity> expiredEvents = eventEntities.stream().filter((event)->event.getEventDate().isBefore(LocalDateTime.now())).toList();
        expiredEvents.stream().forEach(expiredEvent -> expiredEvent.setExpired(true));
        eventRepository.saveAll(expiredEvents);

        log.info("ActionLog.checkEventExpire.end");
    }

    public BaseResponseDto<List<EventRespDto>> getAllEvents(Boolean expired) {
        log.info("ActionLog.getAllEvents.start");

        List<EventEntity> eventEntities = eventRepository.findAll();
        var filteredEvents = eventEntities.stream().filter(eventEntity -> eventEntity.getExpired()==null || eventEntity.getExpired().equals(expired)).collect(Collectors.toList());
        List<EventRespDto> eventRespDtos = filteredEvents.stream().map(eventMapper::mapToRespDto).toList();

        log.info("ActionLog.getAllEvents.end");
        return BaseResponseDto.success(eventRespDtos);
    }

    public BaseResponseDto<EventRespDto> getEventById(Long id){
        log.info("ActionLog.getEventById.start id: {}",id);

        EventEntity event = findEvent(id);
        EventRespDto eventRespDto = eventMapper.mapToRespDto(event);

        log.info("ActionLog.getEventById.end id: {}",id);
        return BaseResponseDto.success(eventRespDto);
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

    public BaseResponseDto<EventRespDto> editEvent(Long id, EventReqDto eventReqDto){
        log.info("ActionLog.editEvent.start id: {}, eventReqDto: {}", id,eventReqDto);
        EventEntity eventEntity = findEvent(id);

        if(eventReqDto.getTitle()!=null){
            eventEntity.setTitle(eventReqDto.getTitle());
        }
        if(eventReqDto.getDescription()!=null){
            eventEntity.setDescription(eventReqDto.getDescription());
        }
        if(eventReqDto.getCategory()!=null){
            eventEntity.setCategory(eventReqDto.getCategory());
        }
        if(eventReqDto.getEventDate()!=null){
            eventEntity.setEventDate(eventReqDto.getEventDate());
        }
        if(eventReqDto.getTickets()!=null){
            List<TicketEntity> ticketEntity = eventReqDto.getTickets().stream().map(ticketMapper::mapToEntity).toList();
            eventEntity.setTickets(ticketEntity);
        }
        if(eventReqDto.getPerformers()!=null){
            List<PerformerEntity> performerEntities = eventReqDto.getPerformers().stream().map(performerMapper::mapToEntity).toList();
            eventEntity.setPerformers(performerEntities);
        }
        if(eventReqDto.getCompanyId()!=null){
            CompanyEntity company = companyRepository.findById((long)eventReqDto.getCompanyId()).orElse(null);
            eventEntity.setCompany(company);
        }
        if(eventReqDto.getPlaceId()!=null){
            PlaceEntity place = placeRepository.findById((long)eventReqDto.getPlaceId()).orElse(null);
            eventEntity.setPlace(place);
        }
        EventRespDto eventRespDto = eventMapper.mapToRespDto(eventEntity);
        log.info("ActionLog.editEvent.end id: {}, eventReqDto: {}", id,eventReqDto);
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
