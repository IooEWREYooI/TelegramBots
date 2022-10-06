package Telegram.Bots.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.*;

import static Telegram.Bots.BotsApplication.getPathsWithCalendarFiles;
import static Telegram.Bots.BotsApplication.isTest;
import static Telegram.Bots.config.BotsConfig.*;

@Component
public class U_32_bot extends TelegramLongPollingBot {

    private Logger log = LoggerFactory.getLogger(U_32_bot.class);

    String text = "";

    @Override
    public void onUpdateReceived(Update update){
        log.info("Получено сообщение от {} с текстом : {}", update.getMessage().getFrom().getFirstName(), update.getMessage().getText());
        if (update.hasMessage() && update.getMessage().hasText()) {
            String command = update.getMessage().getText();
            if (command.equalsIgnoreCase("Понедельник")) {
                sendMessage(update, getTextFromFile("monday.txt"));
            }
            else if (command.equalsIgnoreCase("Вторник")) {
                sendMessage(update, getTextFromFile("tuesday.txt"));
            }
            else if (command.equalsIgnoreCase("Среда")) {
                sendMessage(update, getTextFromFile("wednesday.txt"));
            }
            else if (command.equalsIgnoreCase("Четверг")) {
                sendMessage(update, getTextFromFile("thursday.txt"));
            }
            else if (command.equalsIgnoreCase("Пятница")) {
                sendMessage(update, getTextFromFile("friday.txt"));
            }
            else if (command.toUpperCase().contains("ЯША") || command.toUpperCase().contains("ЯКОВ")|| command.toUpperCase().contains("ЯШКА")) {
                text = "_О нет, ты написал про того, чье имя нельзя называть..._\n";
                sendMessage(update, text);
            }
            else if (update.getMessage().getFrom().getUserName().equals("oolllEWREYllloo") && command.startsWith("/update ")){
                var commandList = Arrays.stream(command.split(" ")).toList();
                var commandIter = Arrays.stream(command.split(" ")).toList().subList(2, Arrays.stream(command.split(" ")).toList().size() - 1).iterator();
                StringBuilder textBuilder = new StringBuilder();
                if (commandList.get(1).equalsIgnoreCase("понедельник")){
                    while(commandIter.hasNext())
                        textBuilder.append(commandIter.next()+" ");
                    saveText(getPathsWithCalendarFiles+"monday.txt", textBuilder.toString());
                    sendMessage(update, "Расписание понедельника изменено на:\n"+getTextFromFile("monday.txt"));
                }
                else if (commandList.get(1).equalsIgnoreCase("вторник")){
                    while(commandIter.hasNext())
                        textBuilder.append(commandIter.next()+" ");
                    saveText(getPathsWithCalendarFiles+"tuesday.txt", textBuilder.toString());
                    sendMessage(update, "Расписание вторника изменено на:\n"+getTextFromFile("tuesday.txt"));
                }
                else if (commandList.get(1).equalsIgnoreCase("среда")){
                    while(commandIter.hasNext())
                        textBuilder.append(commandIter.next()+" ");
                    saveText(getPathsWithCalendarFiles+"wednesday.txt", textBuilder.toString());
                    sendMessage(update, "Расписание среды изменено на:\n"+getTextFromFile("wednesday.txt"));
                }
                else if (commandList.get(1).equalsIgnoreCase("четверг")){
                    while(commandIter.hasNext())
                        textBuilder.append(commandIter.next()+" ");
                    saveText(getPathsWithCalendarFiles+"thursday.txt", textBuilder.toString());
                    sendMessage(update, "Расписание четверга изменено на:\n"+getTextFromFile("thursday.txt"));
                }
                else if (commandList.get(1).equalsIgnoreCase("пятница")){
                    while(commandIter.hasNext())
                        textBuilder.append(commandIter.next()+" ");
                    saveText(getPathsWithCalendarFiles+"friday.txt", textBuilder.toString());
                    sendMessage(update, "Расписание пятницы изменено на:\n"+getTextFromFile("friday.txt"));
                }
            }
            else if (command.equals("/start")) {
                if (LocalDate.now().getDayOfWeek() == java.time.DayOfWeek.TUESDAY){
                    text = "_Приветствую тебя в боте для удобной выгрузки расписания занятий и еще каких-либо плюшек, введи день недели чтобы начать_\n"
                            + "\nПример: \n"
                            + "\n" + getTextFromFile("tuesday.txt");
                }
                else if (LocalDate.now().getDayOfWeek() == java.time.DayOfWeek.WEDNESDAY){
                    text = "_Приветствую тебя в боте для удобной выгрузки расписания занятий и еще каких-либо плюшек, введи день недели чтобы начать_\n"
                            + "\nПример: \n"
                            + "\n" + getTextFromFile("wednesday.txt");
                }
                else if (LocalDate.now().getDayOfWeek() == java.time.DayOfWeek.THURSDAY){
                    text = "_Приветствую тебя в боте для удобной выгрузки расписания занятий и еще каких-либо плюшек, введи день недели чтобы начать_\n"
                            + "\nПример: \n"
                            + "\n" + getTextFromFile("thursday.txt");
                }
                else if (LocalDate.now().getDayOfWeek() == java.time.DayOfWeek.FRIDAY){
                    text = "_Приветствую тебя в боте для удобной выгрузки расписания занятий и еще каких-либо плюшек, введи день недели чтобы начать_\n"
                            + "\nПример: \n"
                            + "\n" + getTextFromFile("friday.txt");
                }
                else text = "_Приветствую тебя в боте для удобной выгрузки расписания занятий и еще каких-либо плюшек, введи день недели чтобы начать_\n"
                        + "\nПример: \n"
                        + "\n" + getTextFromFile("monday.txt");
                sendMessage(update, text);
            }
            else sendMessage(update, "Такой команды нет, я только для расписания предназначен...\n"
                        + "\n*Даже у меня есть смысл существования, а у тебя?*");
        }
    }
    public String getTextFromFile(String file){
        BufferedInputStream stream = null;
        byte[] sourcesListByteArray = null;
        try {
            stream = new BufferedInputStream(new FileInputStream(getPathsWithCalendarFiles+file));
            sourcesListByteArray = stream.readAllBytes();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close(); } catch (Exception e){ e.printStackTrace(); }
        }
        return new String(sourcesListByteArray);
    }
    public boolean saveText(String file, String text){
        OutputStreamWriter stream = null;
        try {
            stream = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            stream.write(text.toCharArray());
            stream.flush();
            log.info("В файл {} записан текст: {}", file, text);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                stream.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        if (isTest) {
            return U_32_bot_NAME_TEST;
        }
        return U_32_bot_NAME;
    }
    @Override
    public String getBotToken() {
        if (isTest){
            return U_32_bot_TOKEN_TEST;
        }
        return U_32_bot_TOKEN;
    }
    public void sendMessage(Update update, String text) {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboardRow = new ArrayList<>();

        KeyboardRow row1 = new KeyboardRow();
        KeyboardButton keyButton1 = new KeyboardButton();
        keyButton1.setText("Понедельник");
        row1.add(keyButton1);

        KeyboardButton keyButton2 = new KeyboardButton();
        keyButton2.setText("Вторник");
        row1.add(keyButton2);

        KeyboardRow row2 = new KeyboardRow();
        KeyboardButton keyButton3 = new KeyboardButton();
        keyButton3.setText("Среда");
        row2.add(keyButton3);

        KeyboardButton keyButton4 = new KeyboardButton();
        keyButton4.setText("Четверг");
        row2.add(keyButton4);

        KeyboardRow row3 = new KeyboardRow();
        KeyboardButton keyButton5 = new KeyboardButton();
        keyButton5.setText("Пятница");
        row3.add(keyButton5);

        keyboardRow.add(row1);
        keyboardRow.add(row2);
        keyboardRow.add(row3);
        keyboardMarkup.setKeyboard(keyboardRow);

        try {
            execute(
                    SendMessage.builder()
                            .chatId(update.getMessage().getChatId())
                            .parseMode("Markdown")
                            .text(text)
                            .replyMarkup(keyboardMarkup)
                            .build());
        }
        catch (TelegramApiException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }

    }
}
