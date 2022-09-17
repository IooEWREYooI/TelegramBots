package Telegram.Bots;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import service.U_32_bot;
import service.u_bablo_bot;

@Configuration
public class AppConfig {

    public boolean Test = true;

    @Bean
    public void u_bablo_bot_reg(){
        registartion(new u_bablo_bot());
    }

    @Bean
    public void U_32_bot_reg(){
        registartion(new U_32_bot());
    }

    public void registartion(TelegramLongPollingBot bot){
        try {
            TelegramBotsApi TelegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            TelegramBotsApi.registerBot(bot);
        } catch(TelegramApiException e) {
            System.out.println("Ошибка в "+bot.getBotUsername());
            e.printStackTrace();
        }
        System.out.println("Бот "+bot.getBotUsername()+" -> успешно запущен");
    }
}
