package settings;

import java.util.*;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class CommandsBot extends TelegramLongPollingBot {
	
	@Override
	public String getBotUsername() {
		return "YOUR_BOT_NAME";
	}
	@Override
	public String getBotToken() {
		return "YOUR_TOKEN";
	}
	
	private void sendWithOutURL(Message message) {
		InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
		InlineKeyboardButton button = new InlineKeyboardButton();
		
		button.setText("ТЫК");
		button.setCallbackData("start0");
		
		List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
		keyboardButtonsRow.add(button);
		
		List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
		rowList.add(keyboardButtonsRow);
		
		keyboard.setKeyboard(rowList);
		
		try {
			execute(
					SendMessage.builder()
					.chatId(message.getChatId())
					.parseMode("Markdown")
					.text("Нихера не понял, но очень интересно!")
					.replyMarkup(keyboard)
					.build());
			}
				catch (TelegramApiException e) {
					e.printStackTrace();
				}
	}
	
	@Override
	public void onUpdateReceived(Update update) {
		
		if (update.hasMessage() && update.getMessage().hasText())
		{
			String messageText = update.getMessage().getText();
			Message command = update.getMessage();	
			long chatID = update.getMessage().getChatId();
			
			switch (messageText) {
			
			case "/start":
				startAnswer(command);
				break;
				
			default:
				try {
					execute(
							SendMessage.builder()
							.chatId(command.getChatId())
							.parseMode("Markdown")
							.text("Нихера не понял, но очень интересно!")
							.build());
					}
						catch (TelegramApiException e) {
							e.printStackTrace();
						}
				break;
			}
		}
		else if (update.hasCallbackQuery())
		{
			if (update.getCallbackQuery().getData().equals("start0"))
			{
				try {
					execute(
							SendMessage.builder()
							.chatId(update.getCallbackQuery().getMessage().getChatId())
							.parseMode("Markdown")
							.text("_Hello World!_")
							.build());
					}
						catch (TelegramApiException e) {
							e.printStackTrace();
						}
			}
		}
	}

	
	public void startAnswer(Message command) {
		sendWithOutURL(command);
	}
	
}
