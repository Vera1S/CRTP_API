package com.example.chat_bot_job;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("vacancies");
    }
}
