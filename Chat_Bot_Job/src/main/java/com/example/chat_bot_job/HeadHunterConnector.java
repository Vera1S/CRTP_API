package com.example.chat_bot_job;

import com.example.chat_bot_job.dto.JobListDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class HeadHunterConnector {

    private final RestTemplate restTemplate;

/*Определиться с .queryParam (какие нужны параметры и как их передать)
Внедрить класс НН конектор в шедуллер
В шедулер запустить метод getData
Проверить работоспособность и то что приходит в строку
Из строки получить список объектов (вакансий) для дальнейшей работы
   */

    Collection<JobListDTO> getData(){
        Collection<JobListDTO> pagesVacancy = new ArrayList<>();
        int countPage = 1;
        for (int i = 0; i < countPage; i++) {
            String url = UriComponentsBuilder.fromHttpUrl("https://api.hh.ru/vacancies")
                    .queryParam("text", "Java spring")
                    .queryParam("page", i)
                    .queryParam("date_from", LocalDate.now().toString())
                    .toUriString();

            try {
                JobListDTO response = restTemplate.getForObject(url, JobListDTO.class);
                if (response != null) {
                    log.info("New vacancies: {}", response);
                    countPage = response.getPages();
                    pagesVacancy.add(response);
                } else {
                    log.info("No response from hh.ru API");
                }
            } catch (Exception e) {
                log.error("Error fetching new vacancies", e);
            }
                    }
        return pagesVacancy;
    }
}
