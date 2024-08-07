package com.example.chat_bot_job;

import com.example.chat_bot_job.dto.JobSalaryDTO;
import com.example.chat_bot_job.dto.JobSnippetDTO;
import com.example.chat_bot_job.dto.JobVacancyDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class JobVacancyConvertor {

    public JobVacancy convert (JobVacancyDTO jobVacancyDTO){
        JobVacancy jobVacancy = new JobVacancy();
        jobVacancy.setId(Long.valueOf(jobVacancyDTO.getId()));
        jobVacancy.setCompanyName(jobVacancyDTO.getEmployer().getName());
        jobVacancy.setVacancyName(jobVacancyDTO.getName());

        JobSalaryDTO salary = jobVacancyDTO.getSalary();
        if (salary != null) {
            String salaryString = "От " + salary.getFrom() + " до " + salary.getTo() + " " + salary.getCurrency();
            jobVacancy.setOffer(salaryString);
        }
        JobSnippetDTO snippet = jobVacancyDTO.getSnippet();
        String snippetDescription = snippet.getRequirement() + "\n" + snippet.getResponsibility();
        jobVacancy.setDescription(snippetDescription);

        String dateString = jobVacancyDTO.getCreatedAt().substring(0, jobVacancyDTO.getCreatedAt().length() -5);
        jobVacancy.setCreatedDate(LocalDateTime.parse(dateString));
        jobVacancy.setRemoteWork(RemoteWork.valueOf(jobVacancyDTO.getSchedule().getId().toUpperCase()));

        return jobVacancy;
    }

}
