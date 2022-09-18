package Telegram.Bots;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BotsApplication {

	public static boolean isTest;

	static {
		Scanner console = new Scanner(System.in);
		System.out.println("Test? Y/N");
		String answer = console.nextLine();
		if (answer.equalsIgnoreCase("Y")){
			isTest = true;
		} else if (answer.equalsIgnoreCase("N")) {
			isTest = false;
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(AppConfig.class);
	}

}
