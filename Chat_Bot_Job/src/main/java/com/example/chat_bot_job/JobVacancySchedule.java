package com.example.chat_bot_job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;



@Service
@Slf4j
@RequiredArgsConstructor
public class JobVacancySchedule {

private final HeadHunterConnector headHunterConnector;
    @Scheduled(cron = "0 * * * * *")
    public void newVacancy() {
        headHunterConnector.getData();

    }
}
