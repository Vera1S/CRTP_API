package com.example.chat_bot_job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;



@Service
@Slf4j
public class JobVacancySchedule {


    @Scheduled(cron = "0 0 1 * * *")
    public void newVacancy() {

    }
}
