package com.example.Calculator1o6Bot.service;

import com.example.Calculator1o6Bot.config.BotConfig;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class TelegramBotCalculator extends TelegramLongPollingBot {

    private final BotConfig config;

    public TelegramBotCalculator(BotConfig config){
        this.config = config;
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update.getMessage().getChat().getFirstName() + " - пидор");
        if(update.hasMessage() && update.getMessage().hasText()){
            String massageText = update.getMessage().getText();
            String chatId = update.getMessage().getChatId().toString();
            switch (massageText){
                case "/start":
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                default:
                    sendMessage(chatId, "Команды нет, дебил");
                    break;
            }
        }
    }

    private void startCommandReceived(String chatId, String name){
        String answer = "Привет, " + name + " пошёл нахуй!";
        sendMessage(chatId, answer);
    }

    private  void sendMessage(String chatId, String textToSend){
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(chatId);
        message.setText(textToSend);
    }
}
