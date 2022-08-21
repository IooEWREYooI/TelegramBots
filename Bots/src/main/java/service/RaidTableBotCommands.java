package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import config.SQLraidtable;

public class RaidTableBotCommands extends TelegramLongPollingBot {
	//Методы бота
	public void sendMessage // <- Метод для отправки сообщений message 
	(Message message, String textMessage, boolean disableWebPreview) { 
		try {
			execute(	
					SendMessage.builder()
					.chatId(message.getChatId())
					.parseMode("Markdown")
					.disableWebPagePreview(disableWebPreview)
					.text(textMessage)
					.build());
			}
				catch (TelegramApiException e) {
					e.printStackTrace();
			}
	}
	public void sendMessage // <- Метод для отправки сообщений long chatId 
	(long chatId, String textMessage, boolean disableWebPreview) { 
		try {
			execute(	
					SendMessage.builder()
					.chatId(chatId)
					.parseMode("Markdown")
					.disableWebPagePreview(disableWebPreview)
					.text(textMessage)
					.build());
			}
				catch (TelegramApiException e) {
					e.printStackTrace();
			}
	}
	public void sendInlineKeyBoardMessageWithUrl // <- Отправка сообщения с URL-кнопкой 
	(long chatId, String textOnMessange, String textToKeyButton, String url, String callBackData) {
	     InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
	     InlineKeyboardButton url_button = new InlineKeyboardButton();
	     
	     url_button.setText(textToKeyButton);
	     url_button.setCallbackData(callBackData);
	     url_button.setUrl(url);
	     
	     List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>(); // Создания списка кнопок
	     keyboardButtonsRow.add(url_button);
	     
	     List<List<InlineKeyboardButton>> rowList = new ArrayList<>(); // Создание спикска кнопок??
	     rowList.add(keyboardButtonsRow);
	     
	     keyboard.setKeyboard(rowList);
	     try {
			execute(
	    	SendMessage.builder()
			.chatId(chatId)
			.parseMode("Markdown")
			.text(textOnMessange)
			.replyMarkup(keyboard)
			.build());
	     }
			catch (TelegramApiException e) {
				e.printStackTrace();
			}
	      
	}
	public void sendInlineKeyBoardMessageWithOutUrl // <- Отправка сообщения с кнопкой и считка нажатия 
	(long chatId, String textOnMessange, String textToKeyButton, String callBackData) {
	     InlineKeyboardMarkup keyboard = new InlineKeyboardMarkup();
	     InlineKeyboardButton button = new InlineKeyboardButton();
	     
	     button.setText(textToKeyButton);
	     button.setCallbackData(callBackData);
	     
	     List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>(); // <- Создание массива с кнопками
	     keyboardButtonsRow.add(button);
	     
	     List<List<InlineKeyboardButton>> rowList = new ArrayList<>(); // <- Создание клавиатуры из массива кнопок
	     rowList.add(keyboardButtonsRow);
	     keyboard.setKeyboard(rowList);
	    
	     try {
			execute( 
	    	SendMessage.builder()
			.chatId(chatId)
			.parseMode("Markdown")
			.text(textOnMessange)
			.replyMarkup(keyboard)
			.build());
            
	     }
			catch (TelegramApiException e) {
				e.printStackTrace();
	}
	      
}
	public void ReplyKeyboardMarkup // <- Прикрепленные кнопки к клавиатуре (разработка...) 
	(String textOnKeyboard) { 
		ReplyKeyboardMarkup keyboard = new ReplyKeyboardMarkup(); // Создание клавиатуры
		KeyboardButton keyboardButton = new KeyboardButton(textOnKeyboard); // Создание кнопки с текстом
		
		List<KeyboardButton> keyboardButtonsRow = new ArrayList<>();
	    keyboardButtonsRow.add(keyboardButton);
		
	    List<List<KeyboardButton>> rowList = new ArrayList<>();
	    rowList.add(keyboardButtonsRow);
	     
		keyboard.setKeyboard(null);
	}

	//Данные бота
	@Override
	public String getBotUsername 	// <- Имя бота 
	() { 	
		return "RaidTableBot"; // Тест
		// Основной RaidTableBot
		// Тест VOITY_LIBRARY_BOT
	}
	@Override
	public String getBotToken 		// <- Токен бота 
	() { 		
		return "5539670323:AAFYwYhaOdgEGckDKh0LKYjeUMBPk0eXe6I"; 
		// Основной 5539670323:AAFYwYhaOdgEGckDKh0LKYjeUMBPk0eXe6I
		// Тест 5480231142:AAHkTaPAXwjP4AMIDOS754DkgHCNRu5CrmU
	}
	@Override
	public void onUpdateReceived 	// <- Получение и работа с обновлениями 
	(Update update) {
		boolean isAdmin = false;
        
        ////////// Update содержит сообщение /////////////  
        if (update.hasMessage()) 
        {	
        	Message command = update.getMessage();
        	long chatId = update.getMessage().getChatId(); 
            String userName = update.getMessage().getChat().getUserName();
            
            ///////// Полученное сообщение от Админа ///////////
	        if (update.hasMessage() && userName.equals("oollEWREYlloo") || userName.equals("oolllEWREYllloo") || 			userName.equals("ivashchenkey") || userName.equals("w_e3e7n1qi") || userName.equals("eQuality3105"))
	        {
	        	
	        	if (update.getMessage().getText().equals("/admin")) 
	        	{
	        			isAdmin = true;
	        			adminAnswer(command);
	        	}
	        	else if (update.getMessage().getText().startsWith("/add"))
	        	{
	        			isAdmin = true;
	        			try {
							addAnswer(chatId, update);
						} catch (SQLException e) {
							
							e.printStackTrace();
						}
	        	}
	        	else if (update.getMessage().getText().startsWith("/delete"))
	        	{
	        		isAdmin = true;
	        		try {
						deleteAnswer(update);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Автоматически созданный блок catch
						e.printStackTrace();
					}
	        	}
	        	else if (update.getMessage().getText().startsWith("/update"))
	        	{
	        		isAdmin = true;
	        		try {
						updateAnswer(update);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Автоматически созданный блок catch
						e.printStackTrace();
					}
	        	}
	        			
	        	else {
	        			if (isAdmin == true)
	        			{
			            	isAdmin = false;
			            	defautAnswer(command);
		            	}
	        	}	
	        }
	    }
	        
	        /////////// Алгоритм действий при получении сообщения с текстом //////////// 
        	if (update.hasMessage() && update.getMessage().hasText()) {
        	Message command = update.getMessage();
        	long chatId = update.getMessage().getChatId(); 
            String name = update.getMessage().getChat().getFirstName();
	            
	            switch (update.getMessage().getText()) {
	            
		            case "/start":
		            	startAnswer(command, name);
		                break;
		                
		            case "/help":
		            	helpAnswer(command);
		            	break;
		            	
		            case "/rules":
		            	rulesAnswer(command);
		            	break;
		            	
		            case "/table":
		            	tableAnswer0(command, chatId);
		            	break;
		            	
		            default:
		            	if (isAdmin == false && (update.getMessage().getText().startsWith("/add ")) == false) 
		            	{
		            	defautAnswer(command);
		            	break;
		            	}
		     
			}
	        	}
        
        
        ///////////// Алгоритм действий при нажатии кнопки //////////////////////// 
        else if (update.hasCallbackQuery()) // <- Работа с ответами на кнопки без URL
        {
        	if (update.getCallbackQuery().getData().equals("table0"))
	        		tableAnswer(update.getCallbackQuery().getMessage().getChatId());
        }
	}
		
	
	// Методы для команд
	private void defautAnswer 	// <- Дефолтный ответ 
	(Message message) {
		String defautAnswerMessageText = 
				"*Я тебя не понял, но мне очень интересно!*\n"
				+ " \n_Проблемы?_ Используй - */help*";
		sendMessage (message, defautAnswerMessageText, true);
	}
	private void startAnswer 	// <- Отправка сообщения на команду /start 
	(Message message, String name) { 
		String startAnswerMessageText = 
				"_Здравствуй дорогой_ *"+name+"!* [ ](https://i.ibb.co/VM4vm0D/photo-2022-07-25-06-30-47.jpg)\n" // картинка бота
				+ "\nМеня зовут *@RaidTableBot* , и я буду твоим помощником на рейдах, давай начнем! 🔥\n"
				+ "\n_Что я делаю?_\n"
				+ "\nЯ могу показать таблицу лидеров, а так же обновлять ее значения не выходя из телеграмма\n"
				+ "\n*Список поддерживаемых команд*\n"
				+ "\n*/help* - помощь \n"
				+ "\n*/rules* - показать правила рейдов\n"
				+ "\n*/table* - показать таблицу лидеров\n"
				+ "\n*Текущая версия*\n"
				+ "_v.1.1.0 REALISE by_ [oolllEWREYllloo](https://t.me/oolllEWREYllloo)";
		sendMessage(message, startAnswerMessageText, false);
		
	}
	private void helpAnswer 	// <- Отправка сообщения на команду /help 
	(Message message) {
		
		String helpAnswerMessageText = 
				"_Нужна помощь?_ 🤨 "
				+ "\n*Давай покажу!*\n"
				+ "\n- Команда */rules* - покажет правила рейда\n"
				+ "\n- Команда */start* - покажет вернет тебя обратно к приветсвенному окну\n "
				+ "\n- Команда */table* - покажет тебе таблицу лидеров\n"
				+ "\n- Ссылка на канал - [Crazy Panda Village](https://t.me/crazypandaton)\n"
				+ "\n- Ссылка на [чат](https://t.me/crazypandachat)";
		sendMessage(message, helpAnswerMessageText, true);
	}
	private void rulesAnswer 	// <- Отправка сообщения на команду /rules 
	(Message message) { 
		String rulesAnswerMessageText = 
				 "Итак, *рейд* - это прежде всего какая-то активность в других чатах или под постами, здесь все мы будем заниматься продвижением бренда *Crazy Panda Village* не заметно, но эффективно\n"
				+ "\n🛡 *Правила рейда* 🛡\n"
				+ "\nДля того чтобы стать полноправным участником любого рейда, вам необходимо:\n"
				+ "\n_- Под постом-обьявлении о рейде написать \"+\"_\n"
				+ "_- Поставить на аватарку аккаунта любую панду из_ [списка](https://drive.google.com/drive/folders/1PVuthIhgrT1lxjwnSX0mMHDG3SI6i-Tu?usp=sharing)\n"
				+ "_- Поставить в био (графу о себе) аккаунта наш юзернейм_ *@crazypandaton* _или ссылку на канал https://t.me/crazypandaton_\n"
				+ "_- Не говорить о Crazy Panda Village в чате_\n"
				+ "_- Общаться по темам чата где идет рейд_\n"
				+ "_- Стараться не матюкаться, если в чате есть цензура_\n"
				+ "*- Быть готовым наводить суету* 😎";
		sendMessage(message, rulesAnswerMessageText, true);
	}
	private void tableAnswer0 	// <- Отправка сообщения на команду /table  
	(Message message, long chatId) {
		String textMessage = 
				"🏆 *Хочешь поглядеть на таблицу лидеров?* 🏆\n"
				+ "Ты попал в нужный раздел\n"
				+ "\nСейчас ты увидишь имя, количество баллов и место участников в отдельном сообщении\n"
				+ "\nОсталось только нажать на кнопку\n"
				+ "\n_(если рядом с местом ты видищь 🐼 и у тебя больше 1 балла, то ты претендуешь на NFT)_";
		String textOnButton = "Показать таблицу 🏆";
		String callBackData = "table0";
		
		sendInlineKeyBoardMessageWithOutUrl(chatId, textMessage, textOnButton, callBackData);
	}
	private void tableAnswer	// <- Отправка сообщения на нажатие кнопки в /table 
	(long chatId) {
			String table = SQLraidtable.table.toString();
			sendMessage(chatId, table, false);
		}
	
	private void adminAnswer 	// <- Отправка сообщения на команду /admin
	(Message message) {
		String text = "*Приветствую в админ.панели*"
					+ "\nКоманды для вершения правосудия:\n"
					+ "\n*/update (имя пользователя без @) {колличество баллов}* - Обновить данные пользователя в таблице\n"
					+ "\n*/add (имя пользователя без @) {колличество баллов}* - Добавить пользователя в таблицу\n"
					+ "\n*/delete (имя пользователя без @)* - Удалить пользователя из таблицы\n"
					+ "\nПиши команды только по шаблону, иначе тебя настигнет кара!\n"
					+ "\n*Да прибудет с тобой сила!*";
		sendMessage(message, text, false);
	}
	private void addAnswer 		// <- Отправка сообщения на команду /add (name) {points} 
	(long chatId, Update update) throws SQLException {
		String command = update.getMessage().getText();
		String messageName = "";
		
		char[] arrayOfCommand = command.toCharArray();
    	char first0 = '(';
    	char second0 = ')';
    	char third0 = '{';
    	char fourth0 = '}';
    	int index1 = 0;
    	int index2 = 0;
    	int index3 = 0;
    	int index4 = 0;
    	
    	for(int i = 0; i<arrayOfCommand.length; i++)
    	{
    		if (command.charAt(i) == first0)
    		{
    			index1 = i;
    		}
    		else if (command.charAt(i) == second0)
    		{
    			index2 = i;
    		}
    		else if (command.charAt(i) == third0)
    		{
    			index3 = i;
    		}
    		else if (command.charAt(i) == fourth0)
    		{
    			index4 = i;
    		}
    	}
    	
    	char[] arrayName = new char[index2-index1-1]; 
    	char[] arrayPoints = new char[index4-index3-1];
    	
    	for (int i = 0; i<arrayName.length; i++)
    	{
    		if (i<index2-1)
    			arrayName[i] = arrayOfCommand[index1+1+i];
    		else 
    			arrayName[i] = arrayOfCommand[index2-1-i];
    	}
    	String name = new String(arrayName);
    	
    	
    	for (int i = 0; i<arrayPoints.length; i++)
    	{
    		if (i<index2-1)
    			arrayPoints[i] = arrayOfCommand[index3+1+i];
    		else 
    			arrayPoints[i] = arrayOfCommand[index4-1-i];
    	}
    	int points = Integer.parseInt(new String (arrayPoints));
    	
    	sendMessage(chatId, "Команда принята, идет обработка и внесение пользователя в таблицу!", false);
    	
    	if(SQLraidtable.addUser(name, points) == true) {
        messageName = 
                		"*Пользователь - "+name+"*\n"
                		+ "\nУспешно добавлен!\n";
        	sendMessage(chatId, messageName, false);
    	}
    	else 
    	{
    		messageName = "Ошибка SQL, пользователь не добавлен";
                sendMessage(chatId, messageName, false);
    	}
    			SQLraidtable.table.clear();
                sendMessage(chatId, "Таблица очищена", false);
                
        		try {
        			SQLraidtable.getAllTableSortByPoints("\n\nИмя - @", " , количество баллов - ");
        			sendMessage(chatId, "Запрос выполнен", false);
				} catch (SQLException e) {
					sendMessage(chatId, "*ОШИБКА* -> Запрос не выполнен!", false);
					e.printStackTrace();
				}
        		
    			sendMessage(chatId, 
    				"Команда выполнена, если вы не увидели запись об успешном внесении пользователя, то произошла ошибка\n"
    				+ "Проверить - */table*", false);
	}
	private void deleteAnswer 	// <- Отправка сообщения на команду /delete (name) 
	(Update update) throws ClassNotFoundException, SQLException{
		String command = update.getMessage().getText();
		String messageText = "";
		
		char[] arrayOfCommand = command.toCharArray();
    	char first0 = '(';
    	char second0 = ')';
    	int index1 = 0;
    	int index2 = 0;
    	
    	for(int i = 0; i<arrayOfCommand.length; i++)
    	{
    		if (command.charAt(i) == first0)
    		{
    			index1 = i;
    		}
    		else if (command.charAt(i) == second0)
    		{
    			index2 = i;
    		}
    	}
    	
    	char[] arrayName = new char[index2-index1-1]; 
    	
    	for (int i = 0; i<arrayName.length; i++)
    	{
    		if (i<index2-1)
    			arrayName[i] = arrayOfCommand[index1+1+i];
    		else 
    			arrayName[i] = arrayOfCommand[index2-1-i];
    	}
    	String name = new String(arrayName);
    	
    	
    	if (SQLraidtable.deleteUser(name) == true) {
    		messageText = "*Пользователь - "+name+" успешно удален!*\n";
    		sendMessage(update.getMessage().getChatId(), messageText, false);
    		
    		SQLraidtable.table.clear();
            
            sendMessage(update.getMessage().getChatId(), "Таблица очищена", false);
            SQLraidtable.getAllTableSortByPoints("\n\nИмя - @", " , количество баллов - ");
    		sendMessage(update.getMessage().getChatId(), "Запрос выполнен", false);
    	}
    	else 
    	{
    		messageText = "*Произошла ошибка SQL*\n"
    					+ "Пользователь не удален";
    		sendMessage(update.getMessage().getChatId(), messageText, false);
    	}
	}
	private void updateAnswer 	// <- Отправка сообщения на команду /update (name) {points}
	(Update update) throws ClassNotFoundException, SQLException{
		String command = update.getMessage().getText();
		String messageText = "";
		
		char[] arrayOfCommand = command.toCharArray();
    	char first0 = '(';
    	char second0 = ')';
    	char third0 = '{';
    	char fourth0 = '}';
    	int index1 = 0;
    	int index2 = 0;
    	int index3 = 0;
    	int index4 = 0;
    	
    	for(int i = 0; i<arrayOfCommand.length; i++)
    	{
    		if (command.charAt(i) == first0)
    		{
    			index1 = i;
    		}
    		else if (command.charAt(i) == second0)
    		{
    			index2 = i;
    		}
    		else if (command.charAt(i) == third0)
    		{
    			index3 = i;
    		}
    		else if (command.charAt(i) == fourth0)
    		{
    			index4 = i;
    		}
    	}
    	
    	char[] arrayName = new char[index2-index1-1]; 
    	char[] arrayPoints = new char[index4-index3-1];
    	
    	for (int i = 0; i<arrayName.length; i++)
    	{
    		if (i<index2-1)
    			arrayName[i] = arrayOfCommand[index1+1+i];
    		else 
    			arrayName[i] = arrayOfCommand[index2-1-i];
    	}
    	String name = new String(arrayName);
    	
    	
    	for (int i = 0; i<arrayPoints.length; i++)
    	{
    		if (i<index2-1)
    			arrayPoints[i] = arrayOfCommand[index3+1+i];
    		else 
    			arrayPoints[i] = arrayOfCommand[index4-1-i];
    	}
    	int points = Integer.parseInt(new String (arrayPoints));
    	
    	sendMessage(update.getMessage().getChatId(), "Команда принята, идет обработка и внесение в таблицу!", false);
    	
		if (SQLraidtable.updatePoints(name, points) == true)
		{
			messageText = "Пользовательские баллы  - "+name+", успешно обновлены на *"+points+"*";
			sendMessage(update.getMessage().getChatId(), messageText, false);
		}
		else 
		{
			messageText = "Произошла ошибка SQL";
			sendMessage(update.getMessage().getChatId(), messageText, false);
		}
		
		SQLraidtable.table.clear();
        
        sendMessage(update.getMessage().getChatId(), "Таблица очищена", false);
        SQLraidtable.getAllTableSortByPoints("\n\nИмя - @", " , количество баллов - ");
		sendMessage(update.getMessage().getChatId(), "Запрос выполнен", false);
		
		sendMessage(update.getMessage().getChatId(), 
				"Команда выполнена, если вы не увидели запись об успешном обновлении баллов пользователя, то произошла ошибка\n"
				+ "Проверить - */table*", false);
	}
}
