package io.yurkovlyanets.testbot.telegram;

import io.yurkovlyanets.testbot.configuration.BotSecretsProvider;
import io.yurkovlyanets.testbot.telegram.commands.util.HelpCommand;
import io.yurkovlyanets.testbot.telegram.commands.util.SettingsCommand;
import io.yurkovlyanets.testbot.telegram.commands.util.StartCommand;
import io.yurkovlyanets.testbot.telegram.keyboards.ReplyKeyboardMaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static io.yurkovlyanets.testbot.util.Constants.ERROR_MESSAGE;

@Component
@Slf4j
public class TestBot extends TelegramLongPollingCommandBot {

	private final BotSecretsProvider botSecretsProvider;

	@Override
	public String getBotUsername() {
		return botSecretsProvider.getBotName();
	}
	@Override
	public String getBotToken() {
		return botSecretsProvider.getToken();
	}


	@Override
	public void processNonCommandUpdate(Update update) {
		var msg = update.getMessage();
		var chatId = msg.getChatId();
		var user = msg.getFrom();
		String userName = (user.getUserName() != null) ? user.getUserName() : user.getFirstName();

		SendMessage answer = new SendMessage();
		answer.setText(ERROR_MESSAGE);
		answer.setChatId(chatId.toString());
		try {
			execute(answer);
		} catch (TelegramApiException e) {
			log.error(String.format("Ошибка %s. Сообщение, не являющееся командой. Пользователь: %s", e.getMessage(),
					userName));
			e.printStackTrace();
		}
	}

	@Override
	public void onRegister() {
		super.onRegister();
	}


	public TestBot(BotSecretsProvider botSecretsProvider, ReplyKeyboardMaker replyKeyboardMaker) {
		super();
		this.botSecretsProvider = botSecretsProvider;

		register(new StartCommand(replyKeyboardMaker));
		register(new HelpCommand(replyKeyboardMaker));
		register(new SettingsCommand(replyKeyboardMaker));
	}

}
