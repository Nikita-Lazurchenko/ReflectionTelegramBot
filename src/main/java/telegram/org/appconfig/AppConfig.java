package telegram.org.appconfig;

import lombok.Data;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import telegram.org.botapi.ReflectionTelegramBot;
import telegram.org.botconfig.TelegramBotConfig;

@Data
@Configuration
public class AppConfig {
    private final TelegramBotConfig botConfig;

    @Bean
    public DefaultBotOptions botOptions() {
        DefaultBotOptions options = new DefaultBotOptions();
        options.setProxyHost(botConfig.getProxyHost());
        options.setProxyPort(botConfig.getProxyPort());
        options.setProxyType(botConfig.getProxyType());

        return new DefaultBotOptions();
    }

    @Bean
    public String botToken(){
        return botConfig.getBotToken();
    }

    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
                = new ReloadableResourceBundleMessageSource();

        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
