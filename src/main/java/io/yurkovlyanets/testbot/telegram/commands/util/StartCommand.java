package io.yurkovlyanets.testbot.telegram.commands.util;

import io.yurkovlyanets.testbot.telegram.keyboards.ReplyKeyboardMaker;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;

import static io.yurkovlyanets.testbot.util.Constants.START_MESSAGE;

/**
 * Команда "Старт"
 */
@Slf4j
public class StartCommand extends AddingKeyboardCommand {

    private static final String IDENTIFIER = "start";
    private static final String DESCRIPTION = "start";

    public StartCommand(ReplyKeyboardMaker replyKeyboardMaker) {
        super(IDENTIFIER, DESCRIPTION, replyKeyboardMaker);
    }

    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        var userName = getUserName(user);

        log.debug("Пользователь {}. Начато выполнение команды {}", userName,
                this.getCommandIdentifier());

        sendAnswer(absSender, chat.getId(), this.getCommandIdentifier(), userName, START_MESSAGE);

        log.debug("Пользователь {}. Завершено выполнение команды {}", userName,
                this.getCommandIdentifier());
    }
}