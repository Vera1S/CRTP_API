package com.example.telegram_botjob;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity (name = "subscriber")

public class Subscriber {

    @Id
    private Long id;

    @Column(name = "chat_id")
    private String chatId;

    @Column(name = "user_name")
    private String userName;

}
