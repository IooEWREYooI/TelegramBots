package TelegramBot.RepeaterBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import service.BotCommands;
@SpringBootApplication
public class RepeaterBotApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(RepeaterBotApplication.class);
		application.setBanner(new Cust()); application.run(args);
		
		BotCommands bot1 = new BotCommands();
		try {
			TelegramBotsApi u_bablo_bot = new TelegramBotsApi(DefaultBotSession.class);
			u_bablo_bot.registerBot(bot1);
			System.out.println("\n\nБот @"+bot1.getBotUsername()+" успешно запущен\n\n");
		}
		catch(TelegramApiException e) {
			System.out.println("Ошибка в "+bot1.getBotUsername());
			e.printStackTrace();
		}
	}
}
