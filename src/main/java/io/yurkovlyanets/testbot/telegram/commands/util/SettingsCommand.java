package io.yurkovlyanets.testbot.telegram.commands.util;

import io.yurkovlyanets.testbot.telegram.keyboards.ReplyKeyboardMaker;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import static io.yurkovlyanets.testbot.util.Constants.SETTINGS_MESSAGE;

/**
 * Команда получения текущих настроек
 */
@Slf4j
public class SettingsCommand extends AddingKeyboardCommand {

    private static final String IDENTIFIER = "setting";
    private static final String DESCRIPTION = "setting";

    public SettingsCommand(ReplyKeyboardMaker replyKeyboardMaker) {
        super(IDENTIFIER, DESCRIPTION, replyKeyboardMaker);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        var userName = getUserName(user);

        log.debug("Пользователь {}. Начато выполнение команды {}", userName,
                this.getCommandIdentifier());

        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName, SETTINGS_MESSAGE);

        log.debug("Пользователь {}. Завершено выполнение команды {}", userName,
                this.getCommandIdentifier());
    }
}