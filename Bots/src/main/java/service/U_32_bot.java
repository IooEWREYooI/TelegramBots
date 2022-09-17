package service;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static Telegram.Bots.BotsApplication.isTest;
import static config.BotsConfig.*;


public class U_32_bot extends TelegramLongPollingBot {
    String text = "Такой команды нет, я только для расписания предназначен...\n"
            + "\n*Даже у меня есть смысл существования, а у тебя?*";
    String MONDAY = "Понедельник\r\n"
            + "*1 - Финансовое и налоговое право* \r\n"
            + "\nПрепод - Бедель А.А\r\n"
            + "*2 - Право социального обеспечения*\r\n"
            + "\nПрепод - Жукова М.И\r\n"
            + "*3 - Организация работы органов и учереждений соц защиты населения и органов ПФР*\r\n"
            + "\nПрепод - Косорукова Е.Н\r\n"
            + "*4 - Право социального обеспечения*\r\n"
            + "\nПрепод - Жукова М.И";
    String TUESDAY = "Вторник\r\n"
            + "*1 - Информационные технологии в проф.деятельности*\r\n"
            + "\nПрепод - Кузнецова Д.И\r\n"
            + "*2 - Страховое дело*\r\n"
            + "\nПрепод - Бедель А.А\r\n"
            + "*3 - Организация работы органов и учереждений соц защиты населения и органов ПФР*\r\n"
            + "\nПрепод - Косорукова Е.Н\r\n"
            + "*4 - Гражданский процесс*\r\n"
            + "\nПрепод - Кузнецова А.А";
    String WEDNESDAY = "Среда\r\n"
            + "*1 - пропуск*\r\n"
            + "*2 - Финансовое и налоговое право* \r\n"
            + "\nПрепод - Бедель А.А\r\n"
            + "*3 - Трудовое право*\r\n"
            + "\nПрепод - Захарова Е.Л\r\n"
            + "*4 - Иностранный язык* \r\n"
            + "\nПрепод - Кузнецова Д.И";
    String THURSDAY = "Четверг \r\n"
            + "*1 - Финансовое и налоговое право* \r\n"
            + "\nПрепод - Бедель А.А\r\n"
            + "*2 - Право социального обеспечения*\r\n"
            + "\nПрепод - Жукова М.И\r\n"
            + "*3 - Физ-ра*\r\n"
            + "\nПрепод - Жулин Е.В\r\n"
            + "*4 - Менеджмент* \r\n"
            + "\nПрепод - Наумова Н.Н";
    String FRIDAY = "Пятница \r\n"
            + "*1 - Трудовое право*\r\n"
            + "\nПрепод - Захарова Е.Л \r\n"
            + "*2 - Гражданский процесс*\r\n"
            + "\nКузнецова А.А\r\n"
            + "*3 - Организация работы органов и учереждений соц защиты населения и органов ПФР*\r\n"
            + "\nПрепод - Косорукова Е.Н ";


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String command = update.getMessage().getText();
            String text = null;
            if(command.equalsIgnoreCase("Понедельник")) {
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
                text = "_Приветствую тебя в боте для удобной выгрузки расписания занятий и еще каких-либо плюшек, введи день недели чтобы начать_\r\n"
                        + "\nПример: *Понедельник*\r\n"
                        + "\n"+MONDAY;
                sendMessage(update, text);
            }
            else sendMessage(update, text);
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
        try {
            execute (SendMessage.builder()
                    .parseMode("Markdown")
                    .chatId(update.getMessage()
                            .getChatId())
                    .text(text)
                    .build());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
