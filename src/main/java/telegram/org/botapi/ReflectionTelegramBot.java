package telegram.org.botapi;



import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


@Setter
@Getter
@RestController
@RequestMapping
public class ReflectionTelegramBot extends TelegramWebhookBot {
    private String botPath;
    private String botUsername;
    private TelegramFacade telegramFacade;

    public ReflectionTelegramBot(DefaultBotOptions options, String botToken, TelegramFacade telegramFacade) {
        super(options, botToken);
        this.telegramFacade = telegramFacade;
    }


    @PostMapping("/")
    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(@RequestBody Update update){
        System.out.println(update);
        SendMessage replyMessageToUser = telegramFacade.handleUpdate(update);

        return replyMessageToUser;
    }
}