package telegram.org.botapi.handlers.fillingprofile;

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
public class FillingProfileHandler implements InputMessageHandler {
    private UserDataCache userDataCache;
    private ReplyMessagesService messagesService;

    @Override
    public SendMessage handle(Message message) {
        if (userDataCache.getUsersCurrentBotState(message.getFrom().getId()).equals(BotState.FILLING_PROFILE)) {
            userDataCache.setUsersCurrentBotState(message.getFrom().getId(), BotState.ASK_NAME);
        }
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.FILLING_PROFILE;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        String usersAnswer = inputMsg.getText();
        long userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();

        BotState botState = userDataCache.getUsersCurrentBotState(userId);

        SendMessage replyToUser = null;

        if (botState.equals(BotState.ASK_NAME)) {
            replyToUser = messagesService.getReplyMessage(chatId, "reply.askName");
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_AGE);
        }
        if (botState.equals(BotState.ASK_AGE)) {
            replyToUser = messagesService.getReplyMessage(chatId, "reply.askAge");
            System.out.println("Name: " + usersAnswer);
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_GENDER);
        }

        if (botState.equals(BotState.ASK_GENDER)) {
            replyToUser = messagesService.getReplyMessage(chatId, "reply.askGender");
            System.out.println("Age: " + usersAnswer);
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_NUMBER);
        }

        if (botState.equals(BotState.ASK_NUMBER)) {
            replyToUser = messagesService.getReplyMessage(chatId, "reply.askNumber");
            System.out.println("Gender: " + usersAnswer);
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_MOOD);
        }

        if (botState.equals(BotState.ASK_MOOD)) {
            replyToUser = messagesService.getReplyMessage(chatId, "reply.askMood");
            System.out.println("Number: " + usersAnswer);
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_EVERYDAY_EVENTS);
        }

        if (botState.equals(BotState.ASK_EVERYDAY_EVENTS)) {
            replyToUser = messagesService.getReplyMessage(chatId, "reply.askEverydayEvents");
            System.out.println("Mood: " + usersAnswer);
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_MATE);
        }

        if (botState.equals(BotState.ASK_MATE)) {
            replyToUser = messagesService.getReplyMessage(chatId, "reply.askMate");
            System.out.println("EverydayEvents: " + usersAnswer);
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_ABOUT_FIND_FRIEND);
        }

        if (botState.equals(BotState.ASK_ABOUT_FIND_FRIEND)) {
            replyToUser = messagesService.getReplyMessage(chatId, "reply.askAboutFindFriend");
            System.out.println("Mate: " + usersAnswer);
            userDataCache.setUsersCurrentBotState(userId, BotState.ASK_EVERYDAY_EVENTS);
        }


        return replyToUser;
    }
}
