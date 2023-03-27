package test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFileJSON {
    public String name_file;
    public List<Integer> list1;
    public List<Integer> list2;


    public ReadFileJSON(String name_file) {
        this.name_file = name_file;
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
    }

    public String getName_file() {
        return name_file;
    }

    public void setName_file(String name_file) {
        this.name_file = name_file;
    }

    public List<Integer> getList1() {
        return list1;
    }

    public void setList1(List<Integer> list1) {
        this.list1 = list1;
    }

    public List<Integer> getList2() {
        return list2;
    }

    public void setList2(List<Integer> list2) {
        this.list2 = list2;
    }

    public void getDatafile(){
        JSONParser parser = new JSONParser();
        try {
            //D:\wordspace\logic-domino-master\data
//            Object obj = parser.parse(new FileReader("D:\\wordspace\\logic-domino-master\\data\\fullsamevalue.json"));
//            Object obj = parser.parse(new FileReader("D:\\wordspace\\logic-domino-master\\data\\2block.json"));
            Object obj = parser.parse(new FileReader("D:\\wordspace\\logic-domino-master\\data\\"+name_file));

            JSONObject jsonObject =  (JSONObject) obj;
            // loop array
            JSONArray player1 = (JSONArray) jsonObject.get("set_play1");
            JSONArray player2 = (JSONArray) jsonObject.get("set_play2");;

            for (Object o: player1){
                list1.add(Integer.parseInt(o.toString()));
            }
            for (Object o: player2){
                list2.add(Integer.parseInt(o.toString()));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
