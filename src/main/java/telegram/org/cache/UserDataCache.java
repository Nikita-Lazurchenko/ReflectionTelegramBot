package telegram.org.cache;

import org.springframework.stereotype.Component;
import telegram.org.botapi.BotState;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDataCache {
    private Map<Long, BotState> usersBotStates = new HashMap<>();


    public void setUsersCurrentBotState(long userId, BotState botState) {
        usersBotStates.put(userId, botState);
    }


    public BotState getUsersCurrentBotState(long userId) {
        BotState botState = usersBotStates.get(userId);
        if (botState == null) {
            botState = BotState.GREETINGS;
        }

        return botState;
    }

}
