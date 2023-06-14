import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

public class AYRDBot extends TelegramLongPollingBot {

    private List<Client> clients;

    public AYRDBot() {
        this.clients = new ArrayList<>();
    }

    @Override
    public String getBotUsername() {
        return "t.me/AYRD_API_bot";
    }

    @Override
    public String getBotToken() {
        return "6216242304:AAFmZz0lQJohtDu1YYYZAXikuwWBaEHb8T4";
    }

    @Override
    public void onUpdateReceived(Update update) {
        iterateThroughClients(getChatID(update));
        //   addMenu();

    }

    private long getChatID(Update update) {
        long chatId = 0;
        if (update.getMessage() != null) {
            chatId = update.getMessage().getChatId();
        } else {
            chatId = update.getCallbackQuery().getMessage().getChatId();//לקיחת ID מתוך לחיצה על כפתור
        }
        return chatId;
    }

    public void updateClient(Client client) {
        client.addRequest();
    }

    public void addClient(long chatId) {
        this.clients.add(new Client(chatId));
    }

    public void iterateThroughClients(long chatId) {
        for (Client current : this.clients) {
            if (current.getChatId() != chatId) {
                addClient(chatId);
            } else {
                updateClient(current);
            }
        }
    }

    private void printUserInfo(Update update) {
        System.out.println("User Name: " + update.getMessage().getFrom().getUserName()
                + " Name: " + update.getMessage().getFrom().getFirstName() + ' ' + update.getMessage().getFrom().getLastName());
    }

    private void send(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Client> mostActiveClient() {
        return this.clients.stream().max(Comparator.comparingInt(Client::getRequestCounter));
    }

    public long clientAmount() {
        return this.clients.size();
    }

    public void addMenu() {
        SendMessage sendMessage = new SendMessage();
        InlineKeyboardButton yesButton = new InlineKeyboardButton("Yes");
        yesButton.setCallbackData("Yes");
        InlineKeyboardButton noButton = new InlineKeyboardButton("No");
        noButton.setCallbackData("No");
        List<InlineKeyboardButton> topRow = Arrays.asList(yesButton, noButton);
        showButton(sendMessage, topRow);
        sendMessage.setText("choose from buttons:");
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }

    private void showButton(SendMessage sendMessage, List<InlineKeyboardButton> buttons) { //i hate niggers
        List<List<InlineKeyboardButton>> keyboard = Arrays.asList(buttons);
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
    }


    public void addMenu(Update update) { // נסיון שני i hate niggers
        long chatId = update.getMessage().getChatId();
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        InlineKeyboardButton yesButton = new InlineKeyboardButton("Yes");
        yesButton.setCallbackData("Yes");
        InlineKeyboardButton noButton = new InlineKeyboardButton("No");
        noButton.setCallbackData("No");
        List<InlineKeyboardButton> topRow = Arrays.asList(yesButton, noButton);
        showButton(sendMessage, topRow);
        sendMessage.setText("choose from buttons:");
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
        //i hate niggers
    }
}

