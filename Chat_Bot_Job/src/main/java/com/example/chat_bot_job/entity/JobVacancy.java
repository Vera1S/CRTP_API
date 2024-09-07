package com.example.chat_bot_job.entity;

import com.example.chat_bot_job.RemoteWork;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "job_vacancy")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobVacancy {

    @Id
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String companyName;

    @Column(columnDefinition = "TEXT")
    private String vacancyName;

    @Column(columnDefinition = "TEXT")
    private String offer;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "remote_work")
    private RemoteWork remoteWork;

}
