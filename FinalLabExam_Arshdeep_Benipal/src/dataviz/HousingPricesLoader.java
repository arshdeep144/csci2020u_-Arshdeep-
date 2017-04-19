package dataviz;
import java.io.*;
import java.net.*;
import java.util.*;

import javafx.collections.*;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
/**
 * Created by 100591622 on 4/11/2017.
 */
public class HousingPricesLoader extends Thread{
    public HousingPricesLoader(){
    }
    public Map<String, List<Integer>> loadPrices(){
        List<Integer> temp;
        Map<String,List<Integer>> tot;
        try {
            String searchUrl = "http://csundergrad.science.uoit.ca/csci2020u/data/housing_prices.json";
            URL url = new URL(searchUrl);
            URLConnection conn = url.openConnection();
            conn.setDoOutput(false);
            conn.setDoInput(true);
            InputStream inStream = conn.getInputStream();
            BufferedReader in = new BufferedReader(new InputStreamReader(inStream));
            JSONParser parser = new JSONParser();
            JSONObject obj = (JSONObject)parser.parse(in);

            JSONArray arr = (JSONArray)obj.get("Year");
            for (int i = 0; i < arr.size(); i++) {
                JSONObject item = (JSONObject)arr.get(i);
                String flat1 = (String)item.get("1 bed flats");
                String flat2 = (String)item.get("2 bed flats");
                String house2 = (String)item.get("2 bed houses");
                String house3 = (String)item.get("3 bed houses");
                String house4 = (String)item.get("4 bed houses");
                temp.add(new int[](flat1, flat2, house2,
                        house3, house4));
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
