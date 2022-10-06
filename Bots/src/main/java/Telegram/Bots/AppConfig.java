package Telegram.Bots;

import Telegram.Bots.service.garik_bot;
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
import Telegram.Bots.service.*;

import java.util.Arrays;

import static Telegram.Bots.BotsApplication.isTest;

@Configuration
@ComponentScan("Telegram.Bots")
public class AppConfig {
    private Logger log = LoggerFactory.getLogger(AppConfig.class);
    @Autowired
    u_bablo_bot u_bablo_bot = new u_bablo_bot();
    @Autowired
    U_32_bot U_32_bot = new U_32_bot();
    @Autowired
    garik_bot garik_bot = new garik_bot();

    @Bean
    public void u_bablo_bot_reg(){
        registartion(u_bablo_bot);
    }

    @Bean
    public void U_32_bot_reg(){
        registartion(U_32_bot);
    }

    @Bean
    public void garik_bot_reg(){
        if (!isTest)
        registartion(garik_bot);
    }

    private void registartion(TelegramLongPollingBot bot){
        try {
            TelegramBotsApi TelegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            TelegramBotsApi.registerBot(bot);
            log.info("Бот {} -> успешно запущен", bot.getBotUsername());
        } catch(TelegramApiException e) {
            log.error("Ошибка в {} \n", bot.getBotUsername());
            Arrays.stream(e.getStackTrace()).forEach(x -> log.error(x.toString()));
        }
    }
}
