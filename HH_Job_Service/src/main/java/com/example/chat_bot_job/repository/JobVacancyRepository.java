package com.example.chat_bot_job.repository;

import com.example.chat_bot_job.entity.JobVacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobVacancyRepository extends JpaRepository<JobVacancy, Long> {

}
