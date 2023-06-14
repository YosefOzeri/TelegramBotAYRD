import java.util.Comparator;
import java.util.List;

public class Client {

    private Long chatID;

    //private String name;
    private int requestCounter;

    public Client(Long chatID){
        this.chatID=chatID;
        this.requestCounter = 0;

    }
    public void addRequest(){
        this.requestCounter++;
    }
    public long getChatId() {
        return this.chatID;
    }

    public int getRequestCounter() {
        return this.requestCounter;
    }
}
