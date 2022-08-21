package Telegram.Bots;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import config.SQLraidtable;
import service.RaidTableBotCommands;
import service.u_bablo_botCommands;

@SpringBootApplication
public class BotsApplication {

	public static void main(String[] args) throws ClassNotFoundException {
		SpringApplication.run(BotsApplication.class, args);
		
		// Указания места JDBC для Spring //
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// Получение таблицы raidtable //
		try {
			SQLraidtable.getAllTableSortByPoints("\n\nИмя - @", " , количество баллов - ");
		} catch (SQLException e) {
			 System.out.println("С таблицей raidtable возникла ошибка ->");
			 e.printStackTrace();
		}
		System.out.println("Таблица raidtable создана и отсортирована по возврастанию баллов");
		//////////////////////////////////////////////////////////////////////////////////////
		
		////// Запуск бота RaidTableBot //////
		RaidTableBotCommands bot0 = new RaidTableBotCommands();
		try {
			TelegramBotsApi RaidTableBot = new TelegramBotsApi(DefaultBotSession.class);
			RaidTableBot.registerBot(bot0);
		} catch (TelegramApiException e) {
			System.out.println("Ошибка в "+bot0.getBotUsername());
			e.printStackTrace();
		}
		System.out.println("Бот "+bot0.getBotUsername()+" успешно запущен");
		////////////////////////////////////////////////////////
		
		
		////// Запуск бота u_bablo_bot //////
		u_bablo_botCommands bot1 = new u_bablo_botCommands();
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
