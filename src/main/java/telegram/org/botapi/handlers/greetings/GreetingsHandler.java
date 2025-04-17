package telegram.org.botapi.handlers.greetings;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import telegram.org.botapi.BotState;
import telegram.org.botapi.InputMessageHandler;
import telegram.org.cache.UserDataCache;
import telegram.org.service.ReplyMessagesService;

@Slf4j
@AllArgsConstructor
@Component
public class GreetingsHandler implements InputMessageHandler {
    private UserDataCache userDataCache;
    private ReplyMessagesService messagesService;


    @Override
    public SendMessage handle(Message message) {
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.GREETINGS;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        long userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();

        SendMessage replyToUser = messagesService.getReplyMessage(chatId,"reply.greetings");
        userDataCache.setUsersCurrentBotState(userId, BotState.FILLING_PROFILE);

        return replyToUser;
    }

}
