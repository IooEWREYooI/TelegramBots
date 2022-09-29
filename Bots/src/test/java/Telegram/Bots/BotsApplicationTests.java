package Telegram.Bots;

import Telegram.Bots.service.U_32_bot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
class BotsApplicationTests {
	U_32_bot bot = new U_32_bot();

	@Test
	void TextWithOutNull() {
		assertFalse(bot.MONDAY.contains("null"));
		assertFalse(bot.TUESDAY.contains("null"));
		assertFalse(bot.WEDNESDAY.contains("null"));
		assertFalse(bot.WEDNESDAY.contains("null"));
		assertFalse(bot.FRIDAY.contains("null"));
	}

}
