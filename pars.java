import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class pars {
    private static String date;
    public static HashMap<String, String> hashMap;
    public static HashMap<String, HashMap<String, String>> hashMapHashMap;
    private static Date data;
    private static boolean  dt;
    //Data data;

    public pars() {
        hashMap = new HashMap<>();
        hashMapHashMap = new HashMap<String, HashMap<String, String>>();
        data = new Date();


    }

    static void newPars(String id, boolean dt) {
        //String say = null;
        //hashMap = new HashMap<>();
        String ddate = "";
        if(dt){
            ddate = getData();
        }else{
            ddate = getmyDate();
        }


        try {
            //date = "25.10.2020";
            Document document = Jsoup.connect("https://bank.gov.ua/ua/markets/exchangerates?date=" + ddate + "&period=daily").get();
            Elements elements = document.select("tbody");
            for (Element element : elements.select("tr")) {
                //.put(element.text().split(" ")[2], element.text());
                hashMap.put(element.text().split(" ")[1], element.text());
                //say = element.text();
            }
            hashMapHashMap.put(id, hashMap);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    static String say(String id, String key) {
        return hashMapHashMap.get(id).get(key);
    }
    static void daleteMap(String id){
        hashMapHashMap.remove(id);
    }


    public static String getData(){
        Date data = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        return simpleDateFormat.format(data);
    }
    public static void setmyDate(String Date) {
        date = Date;
    }
    public static String getmyDate(){
        return date;
    }
}



