package Telegram.Bots;
import java.time.LocalTime;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BotsApplication {
	public static boolean isTest;
	public static String getPathsWithCalendarFiles = "C:\\Users\\Яков Мануилов\\IdeaProjects\\TelegramBots\\Bots\\";

	public static void main(String[] args) throws InterruptedException {
		Scanner console = new Scanner(System.in);
		System.out.println("Test? Y/N");
			String answer = console.nextLine();
			if (answer.equalsIgnoreCase("Y")) {
				isTest = true;
			} else if (answer.equalsIgnoreCase("N")) {
				isTest = false;
			}
		System.out.println("Введите директорию с файлами расписания, пример:\n C:\\Users\\yasha\\IdeaProjects\\TelegramBots\\Bots\\ <- в папке directory ");
		getPathsWithCalendarFiles = console.nextLine();
			while(getPathsWithCalendarFiles.contains("/") && getPathsWithCalendarFiles.charAt(getPathsWithCalendarFiles.length() - 1) != '/' ||
			      getPathsWithCalendarFiles.contains("\\") && getPathsWithCalendarFiles.charAt(getPathsWithCalendarFiles.length() - 1) != '\\') {
				System.out.println("Директория "+getPathsWithCalendarFiles+" не корректна");
				getPathsWithCalendarFiles = console.nextLine();
			}
		System.out.println("Директория введена -> "+getPathsWithCalendarFiles);
		SpringApplication.run(AppConfig.class);
	}

}
