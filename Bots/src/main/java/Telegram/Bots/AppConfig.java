package Telegram.Bots;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import Telegram.Bots.service.U_32_bot;
import Telegram.Bots.service.u_bablo_bot;
@Configuration
@ComponentScan("Telegram.Bots")
public class AppConfig {
    private Logger log = LoggerFactory.getLogger(AppConfig.class);

    @Autowired
    u_bablo_bot u_bablo_bot = new u_bablo_bot();
    @Autowired
    U_32_bot U_32_bot = new U_32_bot();

    @Bean
    public void u_bablo_bot_reg(){
        registartion(u_bablo_bot);
    }

    @Bean
    public void U_32_bot_reg(){
        registartion(U_32_bot);
    }

    private void registartion(TelegramLongPollingBot bot){
        try {
            TelegramBotsApi TelegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            TelegramBotsApi.registerBot(bot);
        } catch(TelegramApiException e) {
            log.error("Ошибка в {} \n", bot.getBotUsername());
            e.printStackTrace();
        }
        log.info("Бот {} -> успешно запущен", bot.getBotUsername());
    }
}
