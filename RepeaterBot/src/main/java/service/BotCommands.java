package service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.*;
import java.util.*;

import java.util.Timer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class BotCommands extends TelegramLongPollingBot {
	public final LocalDateTime timeNow = LocalDateTime.now();
	public ArrayList<String> texts = new ArrayList<String>();
	public String fileForTextPath = "";
	public long id = 0;
	
	private void sendMessage(Update update, String text) {
		try {
			execute(SendMessage.builder()
			 .parseMode("Markdown")
			 .text(text)
			 .chatId(update.getMessage().getChatId())
			 .build());
		} catch (TelegramApiException e) {
			System.out.println("Проблема в отправке сообщения\n");
			e.printStackTrace();
		}
	}
	private void sendMessage(Long chatId, String text) {
		try {
			execute(SendMessage.builder()
			 .parseMode("Markdown")
			 .text(text)
			 .chatId(chatId)
			 .build());
		} catch (TelegramApiException e) {
			System.out.println("Проблема в отправке сообщения\n");
			e.printStackTrace();
		}
	}
	private static String readUsingBufferedReader(String fileName)  {
        BufferedReader reader = null;
		try {
			reader = new BufferedReader( new FileReader (fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        try {
			while( ( line = reader.readLine() ) != null ) {
			    stringBuilder.append( line );
			    stringBuilder.append( ls );
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        finally {
        	try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return stringBuilder.toString();
    }

	@Override
	public String getBotUsername() {
		return "BotManagerHRbot";
	}
	@Override
	public String getBotToken() {
		return "5661764437:AAE645vP1YCEqJzA4IjQftVSahhr47vNvFc";
	}	
	
	@Override
	public void onUpdateReceived(Update update) {
		String MessageText = update.getMessage().getText();
		if (update.hasMessage() && update.getMessage().hasText()&& 
			update.getMessage().getFrom().getUserName().equals("Kdimit") || 
			update.getMessage().getFrom().getUserName().equals("Pontiy")) {
			
			if (MessageText.startsWith("/path")) {
				String text = update.getMessage().getText();
				try {
					fileForTextPath = text.substring(text.indexOf(" ")+1, text.length());
					sendMessage(update, "Ваш путь к файлу успешно добавлен: \n\n"+fileForTextPath);
				} catch (Exception e) {
					e.printStackTrace();
					sendMessage(update, "Произошла ошибка, путь не добавлен :\n\n"+e.getStackTrace());
				}
			}
			
			else if (MessageText.equals("/scan")) {
				if (!(fileForTextPath.equals(""))) {
					texts.clear();
					String fileData = readUsingBufferedReader(fileForTextPath); // {asasssa | 50},{sdasdasd | 5},
						String[] textsArray = fileData.split("=!");
						for(var i: textsArray) {
							String text = i.substring(i.indexOf("{")+1, i.length()-1);
							texts.add(text);
						}
						sendMessage(update,"Список сообщений успешно обновлен");
				}
				else sendMessage(update, "Вы не указали путь к файлу через команду */path*, текст не добавлен, повторите попытку!");
			}
			
			else if(MessageText.startsWith("/add")) {
				String text = MessageText.substring(MessageText.indexOf(" ")+1,MessageText.length());
				sendMessage(update, text);
				String textToFile = "{"+text+"}=!";
				
				if (!(fileForTextPath.equals(""))) {
					try (FileOutputStream file = new FileOutputStream(Path.of(fileForTextPath).toFile(), true)){
						file.write(textToFile.getBytes());
						texts.add(text);
						sendMessage(update,"Текст добалвлен");
					} catch (IOException e) {
						e.printStackTrace();
						sendMessage(update,"Файл для записи не найден, убедитесь в правильности написания пути к файлу - "+fileForTextPath);
					}
				}
				else 
					sendMessage(update, "Вы не указали путь к файлу через команду */path*, текст не добавлен, повторите попытку!");
				
			}
			
			else if(MessageText.startsWith("/put")) {
				int numOfText = Integer.parseInt(MessageText.substring(MessageText.indexOf(" ")+1,MessageText.length()))-1;
				if (numOfText != -1) {
					String message = texts.get(numOfText);
					String messageText = message.substring(0,message.indexOf("|"));
					int messageTimer = Integer.parseInt(message.substring(message.indexOf("|")+1,message.length()));
					if(id !=0) {
					sendMessage(update,"Ваше сообщение с текстом - "+messageText+", отправиться через "+messageTimer+" минут.");
					
					new Timer().schedule(new TimerTask() {
						  @Override
						  public void run() {
							 sendMessage(id, messageText);
						  }
						}, messageTimer*60*1000);
					
					}
					else sendMessage(update,"Вы не указали chatId воспользуйтесь командой */id*");
				}
				else sendMessage(update,"Элемента под номером 0 не может быть!");
			}
			
			else if (MessageText.startsWith("/delete")) {
				int numOfText = Integer.parseInt(MessageText.substring(MessageText.indexOf(" ")+1,MessageText.length()))-1;
				if (numOfText != -1 && !(fileForTextPath.equals(""))) {
					try {
						Files.delete(Path.of(fileForTextPath));
						Files.createFile(Path.of(fileForTextPath));
						int i = 0;
						for (var text:texts) {
							if (i!= numOfText) {
								String textToFile = "{"+text+"}=!";
								try (FileOutputStream file = new FileOutputStream(Path.of(fileForTextPath).toFile(), true)){
									file.write(textToFile.getBytes());
								} catch (IOException e) {
									e.printStackTrace();
									sendMessage(update,"Файл для записи не найден, убедитесь в правильности написания пути к файлу - "+fileForTextPath);
								}
							i++;
						}
					}
						texts.clear();
						String fileData = readUsingBufferedReader(fileForTextPath); // {asasssa | 50},{sdasdasd | 5},
						String[] textsArray = fileData.split("=!");
						for(var ii: textsArray) {
								String text = ii.substring(ii.indexOf("{")+1, ii.length()-1);
								texts.add(text);
							}
						sendMessage(update,"Сообщение успешно удалено");
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
				else sendMessage(update,"Элемента под номером 0 не может быть!");
			}
			
			else if (MessageText.equals("/show")) {
				int ii = 1;
				
				for (var i: texts) {
					if(!(i.isEmpty())) {
						sendMessage(update,"Сообщение под номером - "+ii+"\n\n"+i);
						ii++;
					}
				}
			}
			
			else if (MessageText.equals("/start")) {
				String textMessage = 
						"Перед настройкой бота необходимо добавить его в чат администратором, можно без доп.разрешений, но обязательно администратором\r\n"
						+ "\r\n"
						+ "Для начала работы, введите путь к файлу где должен находится список сообщений через команду */path* \r\n"
						+ "_Пример: _ */path C:\\Users\\user\\directory\\texts.txt*\r\n"
						+ "\r\n"
						+ "Если вы перезагрузили сервер, удалили файл, добавили или удалил сообщение, вам необходимо восстановить данные сообщений с таймерами, введите команду - */scan*\r\n"
						+ "\r\n"
						+ "Для настройки чата введите команду */id* и id чата\n_Пример:_ */id -1001633912425*"
						+ "\n"
						+ "Для того чтобы добавить сообщение необходимо воспользоваться командой */add*, в ней же можно ввести таймер для отправки сообщения в чат (по умолчанию минуты), вы пишите текст и разделяете время отправки через  => | <=  этот знак (без пробела)\r\n"
						+ "_Пример: _  /add Какой-либо текст|50 => _(Этот текст отправится через 50 минут)_\r\n"
						+ "Затем введите команду - */scan*\r\n"
						+ "\r\n"
						+ "Подсказки:\r\n"
						+ "*В часе 60 минут\r\n"
						+ "В сутках 1440 минут*\r\n"
						+ "\r\n"
						+ "Для использования шрифтов вам необходимо по особенному помечать слова в тексте, список специальных символов, можно найти ниже:\r\n"
						+ "\\*Жирный текст\\*\r\n"
						+ "\\_Волнистый\\_\r\n"
						+ "\\[Текст](Ссылка) <= Текст со ссылкой\r\n"
						+ "\\`Копируемый текст\\`\r\n"
						+ "\r\n"
						+ "Для удаления сообщения вам нужно воспользоваться командой */delete* \r\n"
						+ "_Пример:_ */delete 1* удаление сообщения под номером 1\r\n"
						+ "Затем введите команду - */scan*\r\n"
						+ "\r\n"
						+ "Для того чтобы \"завести\" таймер, нужно воспользоваться командой */put* в ней надо будет указать номер сообщения и таймер заведется на указанное в сообщение количество минут от текущего значения времени \r\n"
						+ "_Пример:_ */put 1* - получить сообщение под номером 1 с установленным на нем таймере\r\n"
						+ "\r\n"
						+ "Для изменения сообщения вам необходимо:\r\n"
						+ "Удалить сообщение с помощью команды /delete  => Обновить данные по сообщениям  с помощью команды /scan => Добавить сообщение заново с помощью команды /add\r\n"
						+ "\r\n"
						+ "Для просмотра сообщений воспользуйтесь командой */show*";
				sendMessage(update, textMessage);
			}
			else {
				id = update.getMessage().getForwardFromChat().getId();
				sendMessage(update,"Чат добавлен - "+id);
			}
		}
		}
  }

