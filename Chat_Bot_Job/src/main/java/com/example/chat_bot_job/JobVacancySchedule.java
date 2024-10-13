package com.example.chat_bot_job;

import com.example.chat_bot_job.dto.JobListDTO;
import com.example.chat_bot_job.entity.JobVacancy;
import com.example.chat_bot_job.repository.JobVacancyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class JobVacancySchedule {

    private final HeadHunterConnector headHunterConnector;
    private final JobVacancyConvertor jobVacancyConvertor;
    private final JobVacancyRepository jobVacancyRepository;
    private final MessageSender messageSender;

    @Scheduled(cron = "0 0 16 * * *")
    public void newVacancy() {
        Collection<JobListDTO> pagesVacancyResult = headHunterConnector.getData();
        Collection<JobVacancy> listVacancy = pagesVacancyResult.stream()
                .flatMap(jobListDTO -> jobListDTO.getItems().stream())
                .map(jobVacancyDTO -> jobVacancyConvertor.convert(jobVacancyDTO))
                .collect(Collectors.toList());

        jobVacancyRepository.saveAll(listVacancy);
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();


        for (JobVacancy vacancy : listVacancy) {
            try {
                messageSender.sendMessage(mapper.writeValueAsString(vacancy));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
