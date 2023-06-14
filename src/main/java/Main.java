
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
public class  Main {
    public static void main(String[] args) {
        openBot();
       new MainWindow();

    }
    public static void openBot(){
        try {
            TelegramBotsApi botsApi=new TelegramBotsApi((DefaultBotSession.class));
            botsApi.registerBot(new AYRDBot());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }


}