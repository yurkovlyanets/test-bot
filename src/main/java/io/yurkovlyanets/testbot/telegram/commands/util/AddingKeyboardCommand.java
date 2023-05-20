package io.yurkovlyanets.testbot.telegram.commands.util;

import io.yurkovlyanets.testbot.telegram.keyboards.ReplyKeyboardMaker;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * Суперкласс для команд
 */
@Slf4j
public abstract class AddingKeyboardCommand extends BotCommand {

    ReplyKeyboardMaker replyKeyboardMaker;

    AddingKeyboardCommand(String identifier, String description, ReplyKeyboardMaker replyKeyboardMaker) {
        super(identifier, description);
        this.replyKeyboardMaker = replyKeyboardMaker;
    }

    /**
     * Отправка ответа пользователю
     */
    void sendAnswer(AbsSender absSender, Long chatId, String commandName, String userName, String text) {
        SendMessage message = new SendMessage();
        message.enableMarkdown(true);
        message.setChatId(chatId.toString());
        message.setText(text);
        message.setReplyMarkup(replyKeyboardMaker.getMainMenuKeyboard());
        try {
            absSender.execute(message);
        } catch (TelegramApiException e) {
            log.error(String.format("Ошибка %s. Команда %s. Пользователь: %s", e.getMessage(), commandName, userName));
            e.printStackTrace();
        }
    }

    String getUserName(User user) {
        return (user.getUserName() != null) ? user.getUserName() : user.getFirstName();
    }
}