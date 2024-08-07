package com.example.chat_bot_job.dto;

import com.example.chat_bot_job.RemoteWork;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonDeserialize
@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobVacancyDTO {

    private String id;
    private String name;

    @JsonProperty("created_at")
    private String createdAt;

    private JobSalaryDTO salary;
    private JobEmployerDTO employer;
    private JobSnippetDTO snippet;
    private JobScheduleDTO schedule;
    private JobExperienceDTO experience;


    }
