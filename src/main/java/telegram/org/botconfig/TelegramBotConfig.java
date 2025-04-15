package telegram.org.botconfig;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.DefaultBotOptions;

@Component
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@ConfigurationProperties(prefix = "telegrambot")
public class TelegramBotConfig {
    String webHookPath;
    String userName;
    String botToken;

    DefaultBotOptions.ProxyType proxyType;
    String proxyHost;
    int proxyPort;
}
