package com.example.chat_bot_job;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ChatBotJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChatBotJobApplication.class, args);
    }

}
