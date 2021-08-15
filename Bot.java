import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
//import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    Date date = new Date();

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telBot = new TelegramBotsApi();
        try {
            telBot.registerBot(new Bot());
        } catch (TelegramApiException e) {
            System.out.println(e);

        }
        pars pars = new pars();
    }


    public void sendMsg(Message mssg) {
        boolean b = false;
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(mssg.getChatId().toString());
        sendMessage.setReplyToMessageId(mssg.getMessageId());
        /*if(mssg.getText().length()!= 0) {
            sendMessage.setText(pars.say(" "));
        }*/


        switch (mssg.getText().split(">>")[0]) {
            case "/help":
                sendMessage.setText("Cам не ебу как это работает");
                break;
            case "/coursemoney":
                pars.newPars(mssg.getChatId().toString() + mssg.getFrom().getUserName(), true);
                sendMessage.setText("Chek one val, pls\n or enter courrsemoney>>dd.mm.yyyy");
                sendMessage.setText("Today is >>" + pars.getData() +
                        "\nif its true, continue");
                b = true;
                break;
            case "courrsemoney":

                try {
                    sendMessage.setText("Chek one val, pls\n or enter courrsemoney>>dd.mm.yyyy");
                    pars.setmyDate(mssg.getText().split(">>")[1].toString());
                    pars.newPars(mssg.getChatId().toString() + mssg.getFrom().getUserName(), false);
                    b = true;

                    System.out.println(pars.getmyDate());
                } catch (ArrayIndexOutOfBoundsException exception) {
                    sendMessage.setText("after '>>' you must enter the date what you want");
                }
                break;
            case "AUD":
                sendMessage.setText(pars.say(mssg.getChatId().toString() + mssg.getFrom().getUserName(), "AUD"));
                b = true;
                break;
            case "AZN":
                sendMessage.setText(pars.say(mssg.getChatId().toString() + mssg.getFrom().getUserName(), "AZN"));
                b = true;
                break;
            case "BYN":
                sendMessage.setText(pars.say(mssg.getChatId().toString() + mssg.getFrom().getUserName(), "BYN"));
                b = true;
                break;
            case "BGN":
                sendMessage.setText(pars.say(mssg.getChatId().toString() + mssg.getFrom().getUserName(),"BGN"));
                b = true;
                break;
            case "KRW":
                sendMessage.setText(pars.say(mssg.getChatId().toString() + mssg.getFrom().getUserName(),"KRW"));
                b = true;
                break;
            case "HKD":
                sendMessage.setText(pars.say(mssg.getChatId().toString() + mssg.getFrom().getUserName(),"HKD"));
                b = true;
                break;
            case "DKK":
                sendMessage.setText(pars.say(mssg.getChatId().toString() + mssg.getFrom().getUserName(),"DKK"));
                b = true;
                break;
            case "USD":
                sendMessage.setText(pars.say(mssg.getChatId().toString() + mssg.getFrom().getUserName(),"USD"));
                b = true;
                break;
            case "EUR":
                sendMessage.setText(pars.say(mssg.getChatId().toString() + mssg.getFrom().getUserName(),"EUR"));
                b = true;
                break;
            case "EGP":
                sendMessage.setText(pars.say(mssg.getChatId().toString() + mssg.getFrom().getUserName(),"EGP"));
                b = true;
                break;
            case "exit":
                sendMessage.setText("endMessage");
                pars.daleteMap(mssg.getChatId().toString() + mssg.getFrom().getUserName());
                b = false;
                break;

            default:
                sendMessage.setText(mssg.getChatId().toString() + "\n" +
                        mssg.getFrom().getUserName());
        }
        /*pars pars = new pars(date, hashMap);
        pars.Money();*/
        // sendMessage.setText(pars.hashMap.toString());
        try {
            button(sendMessage, b);
            sendMessage(sendMessage);
            System.out.println(mssg.getText());
            System.out.println(mssg);
        } catch (TelegramApiException e) {
            System.out.println(e);
        }

    }

  /*  public String AnswerCallbackCourse() {
        String answer = null;
        answer = pars.say(" ");
        return answer;
    }*/

    @Override
    public void onUpdateReceived(Update update) {
        Message msg = update.getMessage();
        if (msg != null) {
            sendMsg(msg);
        }
    }

    public void button(SendMessage sendMessage, boolean b) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        if (!b) {
            List<KeyboardRow> keyboardRowList = new ArrayList<>();
            KeyboardRow keyfistrow = new KeyboardRow();
            keyfistrow.add(new KeyboardButton("/help"));
            keyfistrow.add(new KeyboardButton("/coursemoney"));
            keyfistrow.add(new KeyboardButton("courrsemoney>>"));
            keyfistrow.add(new KeyboardButton("/speak"));
            keyboardRowList.add(keyfistrow);
            replyKeyboardMarkup.setKeyboard(keyboardRowList);
        } else {
            List<KeyboardRow> keyboardRowList = new ArrayList<>();
            KeyboardRow key1row = new KeyboardRow();
            key1row.add(new KeyboardButton("AUD"));
            key1row.add(new KeyboardButton("AZN"));
            key1row.add(new KeyboardButton("BYN"));
            KeyboardRow key2row = new KeyboardRow();
            key2row.add(new KeyboardButton("BGN"));
            key2row.add(new KeyboardButton("KRW"));
            key2row.add(new KeyboardButton("HKD"));
            KeyboardRow key3row = new KeyboardRow();
            key3row.add(new KeyboardButton("DKK"));
            key3row.add(new KeyboardButton("USD"));
            key3row.add(new KeyboardButton("EUR"));
            KeyboardRow key4row = new KeyboardRow();
            key4row.add(new KeyboardButton("EGP"));
            key4row.add(new KeyboardButton("JPY"));
            key4row.add(new KeyboardButton("PLN"));
            KeyboardRow key5row = new KeyboardRow();
            key5row.add(new KeyboardButton("INR"));
            key5row.add(new KeyboardButton("CAD"));
            key5row.add(new KeyboardButton("HRK"));
            key5row.add(new KeyboardButton("MXN"));
            key5row.add(new KeyboardButton("MDL"));
            key5row.add(new KeyboardButton("ILS"));
            KeyboardRow key6row = new KeyboardRow();
            key6row.add(new KeyboardButton("exit"));
            keyboardRowList.add(key1row);
            keyboardRowList.add(key2row);
            keyboardRowList.add(key3row);
            keyboardRowList.add(key4row);
            keyboardRowList.add(key5row);
            keyboardRowList.add(key6row);

            replyKeyboardMarkup.setKeyboard(keyboardRowList);
        }


    }

    @Override
    public String getBotUsername() {
        return "AgusshacourseBot";
    }

    @Override
    public String getBotToken() {
        return "1640780200:AAG9CsTeSiaqaJyvtQduhhIkX_XwubOSmbY";
    }

}
