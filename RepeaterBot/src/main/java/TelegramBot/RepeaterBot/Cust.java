package TelegramBot.RepeaterBot;

import java.io.PrintStream;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

public class Cust implements Banner {
	@Override
	public void printBanner(Environment arg0, Class<?> arg1, PrintStream arg2) {
		arg2.println(""
				+ ",------. ,--.   ,--. ,------.  ,------. ,--.   ,--.                           \r\n"
				+ "|  .---' |  |   |  | |  .--. ' |  .---'  \\  `.'  /     ,---.  ,--.--.  ,---.  \r\n"
				+ "|  `--,  |  |.'.|  | |  '--'.' |  `--,    '.    /     | .-. | |  .--' | .-. | \r\n"
				+ "|  `---. |   ,'.   | |  |\\  \\  |  `---.     |  |      ' '-' ' |  |    ' '-' ' \r\n"
				+ "`------' '--'   '--' `--' '--' `------'     `--'       `---'  `--'    .`-  /  \r\n"
				+ "                                                                      `---'   \r\n"
				+ "Made by @oollEWREYlloo\n\n");
	}
}
