package com.example.iticketfinal.schedules;

import com.example.iticketfinal.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class EventExpireDateSchedule {
    private final EventService eventService;

    @Scheduled(cron = "0 1 * * * *")
    public void checkEventExpireDateSchedule() {
        log.info("ActionLog.checkEventExpireDateSchedule.start");

        eventService.checkEventExpire();

        log.info("ActionLog.checkEventExpireDateSchedule.end");
    }

}
