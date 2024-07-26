package com.example.chat_bot_job;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table (name = "job_vacancy")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobVacancy {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String companyName;

    @Column
    private String vacancyName;

    @Column
    private String offer;

    @Column
    private String description;

    @Column
    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "remote_work")
    private RemoteWork remoteWork;


}
