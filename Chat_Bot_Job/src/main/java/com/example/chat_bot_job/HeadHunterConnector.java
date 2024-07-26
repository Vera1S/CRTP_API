package com.example.chat_bot_job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;
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

    void getData(){
        String url = UriComponentsBuilder.fromHttpUrl("https://api.hh.ru/vacancies")
                .queryParam("text", "новые вакансии")
                .queryParam("per_page", 5)
                .queryParam("data", )
                .toUriString();

        try {
            String response = restTemplate.getForObject(url, String.class);
            if (response != null) {
                log.info("New vacancies: {}", response);
            } else {
                log.warn("No response from hh.ru API");
            }
        } catch (Exception e) {
            log.error("Error fetching new vacancies", e);
        }

    }
}
