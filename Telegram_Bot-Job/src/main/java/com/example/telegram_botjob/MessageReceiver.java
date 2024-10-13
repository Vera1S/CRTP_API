package com.example.telegram_botjob;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageReceiver {

    private final SubscriberRepository subscriberRepository;
    private final TelegramJobBot telegramJobBot;

    @RabbitListener(queues = "vacanciesQueue")
    public void receiveMessage(String message) {
        List<Subscriber> users = subscriberRepository.findAll();
        for (Subscriber user : users) {

            SendMessage sendMessage = new SendMessage(user.getChatId(), message);

            try {
                telegramJobBot.execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }



    }
}
