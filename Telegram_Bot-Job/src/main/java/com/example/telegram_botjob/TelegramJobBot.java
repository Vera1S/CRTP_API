package com.example.telegram_botjob;

import org.springframework.stereotype.Component;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendSticker;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.stickers.Sticker;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramJobBot extends TelegramLongPollingBot {


    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage()){
            return;
        }
       Message message = update.getMessage();

        if (message.hasText()){
            String telegramText = message.getText();
            SendMessage sendMessage = new SendMessage(String.valueOf(message.getChatId()), telegramText);
            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
      if (message.hasSticker()){
         Sticker sticker = message.getSticker();
          InputFile inputFile = new InputFile(sticker.getFileId());
          SendSticker sendSticker = new SendSticker(String.valueOf(message.getChatId()), inputFile);
          try {
              execute(sendSticker);
          } catch (TelegramApiException e) {
              throw new RuntimeException(e);
          }
      }

    }

    @Override
    public String getBotUsername() {
        return "hhJobBot_bot";
    }

    @Override
    public String getBotToken() {
        return "7508318427:AAGEhUDBD1yRLVQczRzC493T7WTiQ-79aSA";
    }
}
