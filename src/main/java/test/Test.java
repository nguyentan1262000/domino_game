package test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import vn.vietdefi.game.logic.domino.DominoMain;
import vn.vietdefi.game.logic.domino.DonimoGame;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Test {


    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        DominoMain d = new DominoMain();
        DonimoGame game = new DonimoGame(d.createDummyNamePlayers(2), 2, 7);

        //test
        ArrayList<Integer> hand1 = new ArrayList<>();
        ArrayList<Integer> hand2 = new ArrayList<>();



        try {
//            {
//                "set_play1": [1,14,23,27,9,11,10],
//                "set_play2": [8,19,26,12,20,7,24]
//            }
            //D:\wordspace\logic-domino-master\data\fullsamevalue.json
            FileReader obj = new FileReader("D:\\wordspace\\logic-domino-master\\data\\fullsamevalue.json");
            JSONParser parser = new JSONParser();

            JSONObject jsonObject =  (JSONObject) parser.parse(obj);


            // loop array
            JSONArray player1 = (JSONArray) jsonObject.get("set_play1");
            JSONArray player2 = (JSONArray) jsonObject.get("set_play2");

            for (Object o : player1) {
                hand1.add(Integer.parseInt(o.toString()));
            }
            for (Object o : player2) {
                hand2.add(Integer.parseInt(o.toString()));
            }

//            System.out.println();
//            System.out.println("player 2 in hand:");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(hand1);
        System.out.println(hand2);

        if(hand1.get(0) == 0){
            System.out.println("dieu kien bang nhau");
        }

//        game.InitTest(hand1,hand2);
////        for (int i = 0; i < game.numPieces; i++) {
////            System.out.print("["+game.listPlayers.get(0)+"]");
////        }
//        System.out.print("["+game.listPlayers.get(0).getHand().size()+"]");
//            System.out.print("["+game.listPlayers.get(1).getHand().size()+"]");

    }
}
