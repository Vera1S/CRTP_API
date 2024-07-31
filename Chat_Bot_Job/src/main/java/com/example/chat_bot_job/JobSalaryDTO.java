package com.example.chat_bot_job;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@Data
@JsonDeserialize
@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobSalaryDTO {

    private Integer from;
    private Integer to;
    private String currency;
}
