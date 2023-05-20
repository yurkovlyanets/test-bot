package io.yurkovlyanets.testbot.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class BotSecretsProvider {

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String token;
}
