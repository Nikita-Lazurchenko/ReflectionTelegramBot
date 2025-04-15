package telegram.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import telegram.org.botapi.ReflectionTelegramBot;
import telegram.org.botconfig.TelegramBotConfig;

@SpringBootApplication
public class ReflectionTelegramBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReflectionTelegramBotApplication.class, args);
    }

}
