package com.example.Calculator1o6Bot.service;

import com.example.Calculator1o6Bot.config.BotConfig;
import com.vdurmont.emoji.EmojiParser;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Component
public class TelegramBotCalculator extends TelegramLongPollingBot {

    private final BotConfig config;
    int k = 0;

    public TelegramBotCalculator(BotConfig config) throws TelegramApiException {
        this.config = config;
        List<BotCommand> listOfCommands = new ArrayList<>();
        listOfCommands.add(new BotCommand("/start", "fdgs"));
        listOfCommands.add(new BotCommand("/help", "fdgdsf"));
        listOfCommands.add(new BotCommand("/aaaa", "fgfdgdfg"));
        try {
            execute(new SetMyCommands(listOfCommands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {

        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String massageText = update.getMessage().getText();
            String chatId = update.getMessage().getChatId().toString();
            switch (massageText) {
                case "/start":
                    startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    break;
                case "/help":
                    helpCommandReceived(chatId);
                    break;
                default:
                    sendMessage(chatId, "Команды нет, дебил", 2);
                    break;
            }
        } else if(update.hasCallbackQuery()){
            String callBackData = update.getCallbackQuery().getData();
            int messageId = update.getCallbackQuery().getMessage().getMessageId();
            String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
            if(callBackData.equals("FOUR_SHIP")){
                editMessage(chatId,messageId,"Четырёхпалубник");
            } else if(callBackData.equals("TREE_SHIP")){
                editMessage(chatId,messageId,"Трёхпалубник");
            } else if(callBackData.equals("TWO_SHIP")){
                editMessage(chatId,messageId,"Двупалубник");
            } else if (callBackData.equals("ONE_SHIP")){
                editMessage(chatId,messageId,"Однопалубник");
            }
        }
    }

    private synchronized InlineKeyboardMarkup setButtonsShip() {
        InlineKeyboardMarkup markup= new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        var button = new InlineKeyboardButton();
        button.setText(EmojiParser.parseToUnicode(":ship::ship::ship::ship:"+"x1"));
        button.setCallbackData("FOUR_SHIP");
        row.add(button);
        button = new InlineKeyboardButton();
        button.setText(EmojiParser.parseToUnicode(":ship::ship::ship:"+"x2"));
        button.setCallbackData("TREE_SHIP");
        row.add(button);
        rows.add(row);
        row = new ArrayList<>();
        button = new InlineKeyboardButton();
        button.setText(EmojiParser.parseToUnicode(":ship::ship:"+"x3"));
        button.setCallbackData("TWO_SHIP");
        row.add(button);
        button = new InlineKeyboardButton();
        button.setText(EmojiParser.parseToUnicode(":ship:"+"x4"));
        button.setCallbackData("ONE_SHIP");
        row.add(button);
        rows.add(row);
        markup.setKeyboard(rows);
        return markup;
    }

    private synchronized InlineKeyboardMarkup setButtonsShips() {
        InlineKeyboardMarkup markup= new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        List<InlineKeyboardButton> row = new ArrayList<>();
        char[] column = {'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ж', 'З', 'И' ,'К','♞','♘','♚','♔','♛','♕','♟','♙','♝','♗','♜','♖'};
        var button = new InlineKeyboardButton();
        for (int i = 0 ; i<8; i++){
            for (int j = 0; j < column.length; j++){
                button.setText(EmojiParser.parseToUnicode("♔"));
                button.setCallbackData(column.toString() + i);
                row.add(button);
            }
            rows.add(row);
            row = new ArrayList<>();
        }
        markup.setKeyboard(rows);
        return markup;
    }

    private synchronized ReplyKeyboardMarkup setButtonsColumn() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("A");
        row.add("♔");
        row.add("В");
        row.add("Г");
        row.add("Д");
        row.add("Е");
        row.add("Ж");
        row.add("З");
        row.add("И");
        row.add("К");
        rows.add(row);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setKeyboard(rows);
        return keyboardMarkup;
    }

    private synchronized ReplyKeyboardMarkup setButtonsRow() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("0");
        row.add("1");
        row.add("2");
        row.add("3");
        row.add("♔");
        row.add("5");
        row.add("6");
        row.add("7");
        row.add("8");
        row.add("9");
        rows.add(row);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setKeyboard(rows);
        return keyboardMarkup;
    }

    private synchronized ReplyKeyboardMarkup setButtonsNull() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> rows = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Хуита");
        rows.add(row);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setKeyboard(rows);
        return keyboardMarkup;
    }


    private void helpCommandReceived(String chatId) throws TelegramApiException {
        String answer = EmojiParser.parseToUnicode("Так не понятно что ли?:blush:");
        sendMessage(chatId, answer, 3);
    }

    private void startCommandReceived(String chatId, String name) throws TelegramApiException {
        String answer = "Привет, " + name + " пошёл нахуй!";
        sendMessage(chatId, answer,1);
    }

    private void editMessage(String chatId, int messageId, String text) throws TelegramApiException {
        EditMessageText messageText = new EditMessageText();
        messageText.setChatId(chatId);
        messageText.setText(text);
        messageText.setMessageId(messageId);
        execute(messageText);
    }

    private void sendMessage(String chatId, String textToSend, int keyboard) throws TelegramApiException {
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(chatId);
        message.setText(textToSend);
        switch (keyboard){
            case 1:
                message.setReplyMarkup(setButtonsRow());
                break;
            case 2:
                message.setReplyMarkup(setButtonsColumn());
                break;
            case 3:
                message.setReplyMarkup(setButtonsNull());
                message.setReplyMarkup(setButtonsShips());
                break;
            default:
                message.setReplyMarkup(setButtonsNull());
                break;
        }

        execute(message);
    }
}
