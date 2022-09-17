package Telegram.Bots;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BotsApplication {

	public static void main(String[] args) throws ClassNotFoundException {
		// Указания места JDBC для Spring //
		Class.forName("com.mysql.cj.jdbc.Driver");

		SpringApplication.run(AppConfig.class);

	}

}
