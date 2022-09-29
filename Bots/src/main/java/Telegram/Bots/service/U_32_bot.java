package Telegram.Bots.service;

import Telegram.Bots.EntityService.StudentService;
import Telegram.Bots.config.Entity.StudentEntity;
import Telegram.Bots.config.Enums.DayOfWeek;
import Telegram.Bots.config.Enums.Lections;
import Telegram.Bots.config.Enums.Teachers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static Telegram.Bots.BotsApplication.isTest;
import static Telegram.Bots.config.BotsConfig.*;
@Component
public class U_32_bot extends TelegramLongPollingBot {

    @Autowired
    StudentService service = new StudentService();

    private Logger log = LoggerFactory.getLogger(U_32_bot.class);

    String text = "Такой команды нет, я только для расписания предназначен...\n"
            + "\n*Даже у меня есть смысл существования, а у тебя?*";
    public String MONDAY = createDay(DayOfWeek.MONDAY.getTitle(), Lections.FNP.getLection(), Teachers.BAA.getTeacher(),
    Lections.PSO.getLection(), Teachers.SMI.getTeacher(), Lections.PFR.getLection(), Teachers.KEV.getTeacher(),
            Lections.PSO.getLection(), Teachers.SMI.getTeacher());
    public String TUESDAY = createDay(DayOfWeek.TUESDAY.getTitle(), Lections.INF.getLection(), Teachers.KDI.getTeacher(),
    Lections.STR.getLection(), Teachers.BAA.getTeacher(), Lections.PFR.getLection(), Teachers.KEV.getTeacher(),
    Lections.GPR.getLection(), Teachers.KAA.getTeacher());

    public String WEDNESDAY = createDay(DayOfWeek.WEDNESDAY.getTitle(), Lections.NAN.getLection(), Teachers.NAN.getTeacher(), Lections.FNP.getLection(),
            Teachers.BAA.getTeacher(), Lections.TRP.getLection(), Teachers.ZEV.getTeacher(), Lections.ANG.getLection(), Teachers.KDI.getTeacher());
    public String THURSDAY = createDay(DayOfWeek.THURSDAY.getTitle(), Lections.FNP.getLection(),
            Teachers.BAA.getTeacher(), Lections.PSO.getLection(), Teachers.SMI.getTeacher(), Lections.PHI.getLection(), Teachers.ZEV.getTeacher(),
    Lections.MEN.getLection(), Teachers.NNN.getTeacher());
    public String FRIDAY = createFriday(DayOfWeek.FRIDAY.getTitle(),Lections.NAN.getLection(), Teachers.NAN.getTeacher(),
            Lections.GPR.getLection(), Teachers.KAA.getTeacher(), Lections.PFR.getLection(),
    Teachers.KEV.getTeacher(), Lections.TRP.getLection(), Teachers.ZEL.getTeacher());

    @Override
    public void onUpdateReceived(Update update){
        log.info("Получено сообщение от {} с текстом : {}", update.getMessage().getFrom().getFirstName(), update.getMessage().getText());
        LocalDate now = LocalDate.now();
        if (update.hasMessage() && update.getMessage().hasText()) {
            String command = update.getMessage().getText();
            if (command.equalsIgnoreCase("Понедельник")) {
                text = MONDAY;
                sendMessage(update, text);
            }
            else if (command.equalsIgnoreCase("Вторник")) {
                text = TUESDAY;
                sendMessage(update, text);
            }
            else if (command.equalsIgnoreCase("Среда")) {
                text = 	WEDNESDAY;
                sendMessage(update, text);
            }
            else if (command.equalsIgnoreCase("Четверг")) {
                text = THURSDAY;
                sendMessage(update, text);
            }
            else if (command.equalsIgnoreCase("Пятница")) {
                text = FRIDAY;
                sendMessage(update, text);
            }
            else if (command.toUpperCase().contains("ЯША") || command.toUpperCase().contains("ЯКОВ")|| command.toUpperCase().contains("ЯШКА")) {
                text = "_О нет, ты написал про того, чье имя нельзя называть..._\n";
                sendMessage(update, text);
            }
            else if (command.equals("/start")) {
                if (LocalDate.now().getDayOfWeek() == java.time.DayOfWeek.TUESDAY){
                    text = "_Приветствую тебя в боте для удобной выгрузки расписания занятий и еще каких-либо плюшек, введи день недели чтобы начать_\n"
                            + "\nПример: \n"
                            + "\n" + TUESDAY;
                } else if (LocalDate.now().getDayOfWeek() == java.time.DayOfWeek.WEDNESDAY){
                    text = "_Приветствую тебя в боте для удобной выгрузки расписания занятий и еще каких-либо плюшек, введи день недели чтобы начать_\n"
                            + "\nПример: \n"
                            + "\n" + WEDNESDAY;
                } else if (LocalDate.now().getDayOfWeek() == java.time.DayOfWeek.THURSDAY){
                    text = "_Приветствую тебя в боте для удобной выгрузки расписания занятий и еще каких-либо плюшек, введи день недели чтобы начать_\n"
                            + "\nПример: \n"
                            + "\n" + THURSDAY;
                } else if (LocalDate.now().getDayOfWeek() == java.time.DayOfWeek.FRIDAY){
                    text = "_Приветствую тебя в боте для удобной выгрузки расписания занятий и еще каких-либо плюшек, введи день недели чтобы начать_\n"
                            + "\nПример: \n"
                            + "\n" + FRIDAY;
                } else text = "_Приветствую тебя в боте для удобной выгрузки расписания занятий и еще каких-либо плюшек, введи день недели чтобы начать_\n"
                        + "\nПример: \n"
                        + "\n" + MONDAY;
                sendMessage(update, text);
            }
            else if (command.equalsIgnoreCase("Список")){
                sendMessage(update, service.getList().toString());
            }
            else sendMessage(update, text);
        }
    }
    public String createDay(String... args){
        return String.format(
                "*%s* \n\n" +
                        "*8:30 - 9:45 - %s*" +
                        "\nПрепод - _%s_\n" +
                        "\n*10:00 - 11:20 - %s*" +
                        "\nПрепод - _%s_\n" +
                        "\n*11:30 - 12:50 - %s*" +
                        "\nПрепод - _%s_\n" +
                        "\n*13:10 - 14:30 - %s*" +
                        "\nПрепод - _%s_",
                args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8]
        );
    }
    public String createFriday(String... args){
        return String.format("*%s* \n\n" +
                "*8:30 - 9:30 - %s*" +
                "\nПрепод - _%s_\n" +
                "\n*9:40 - 10:40 - %s*" +
                "\nПрепод - _%s_\n" +
                "\n*10:50 - 11:50 - %s*" +
                "\nПрепод - _%s_\n" +
                "\n*12:10 - 13:10 - %s*" +
                "\nПрепод - _%s_",
        args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8]
        );
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

        if (!service.inTable(update.getMessage().getChatId())) {
            StudentEntity entity = new StudentEntity();
            entity.setUser_id(update.getMessage().getChatId());
            entity.setUsername(update.getMessage().getFrom().getUserName());
            service.add(entity);
            log.info("Добавлен пользователь с id {} и username {}", entity.getUser_id(), entity.getUsername());
        }

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
