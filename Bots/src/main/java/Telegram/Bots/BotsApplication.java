package Telegram.Bots;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import service.u_bablo_bot;

@SpringBootApplication
public class BotsApplication {

	public static void main(String[] args) throws ClassNotFoundException {
		SpringApplication.run(BotsApplication.class, args);
		
		// Указания места JDBC для Spring //
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		////// Запуск бота u_bablo_bot //////
		u_bablo_bot bot1 = new u_bablo_bot();
		try {
			TelegramBotsApi u_bablo_bot = new TelegramBotsApi(DefaultBotSession.class);
			u_bablo_bot.registerBot(bot1);
		}
		catch(TelegramApiException e) {
			System.out.println("Ошибка в "+bot1.getBotUsername());
			e.printStackTrace();
		}
		System.out.println("Бот "+bot1.getBotUsername()+" успешно запущен");
		//////////////////////////////////////////////////////
	}

}
