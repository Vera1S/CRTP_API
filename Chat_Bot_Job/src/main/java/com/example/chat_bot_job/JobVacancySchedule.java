package com.example.chat_bot_job;

import com.example.chat_bot_job.dto.JobListDTO;
import com.example.chat_bot_job.dto.JobVacancyDTO;
import com.example.chat_bot_job.repository.JobVacancyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class JobVacancySchedule {

    private final HeadHunterConnector headHunterConnector;
    private final JobVacancyConvertor jobVacancyConvertor;
    private final JobVacancyRepository jobVacancyRepository;

    @Scheduled(cron = "0 * * * * *")
    public void newVacancy() {
        Collection<JobListDTO> pagesVacancyResult = headHunterConnector.getData();
        Collection<JobVacancy> listVacancy = pagesVacancyResult.stream()
                .flatMap(jobListDTO -> jobListDTO.getItems().stream())
                .map(jobVacancyDTO -> jobVacancyConvertor.convert(jobVacancyDTO))
                .collect(Collectors.toList());

        jobVacancyRepository.saveAll(listVacancy);

    }
}
