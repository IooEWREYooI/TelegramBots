package Telegram.Bots;
import java.sql.SQLException;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BotsApplication {

	public static boolean isTest;

	public static void main(String[] args) throws ClassNotFoundException {
		// Указания места JDBC для Spring //
		Class.forName("com.mysql.cj.jdbc.Driver");

		Scanner console = new Scanner(System.in);
		System.out.println("Test? Y/N");
		String answer = console.nextLine();
		if (answer.equals("Y")){
			isTest = true;
		} else if (answer.equals("N")) {
			isTest = false;
		}

		SpringApplication.run(AppConfig.class);

	}

}
