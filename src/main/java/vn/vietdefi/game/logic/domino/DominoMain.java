package vn.vietdefi.game.logic.domino;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import test.ReadFileJSON;
import vn.vietdefi.game.logic.domino.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


// package vn.vietdefi.game.logic.domino;

@SuppressWarnings("unchecked")
public class DominoMain {
    public static Log l = new Log();

    public DominoMain() {
    }

    public static String[] createDummyNamePlayers(int sl) {
        String[] namePlayers = new String[sl];
        for (int i = 0; i < namePlayers.length; i++) {
            namePlayers[i] = "Player " + (i + 1);
        }
        return namePlayers;
    }

    public static int cntPieceSelect(HashMap<String, List> piecesSelect) {
        int cnt = 0;
        Config config = new Config();
        for (String d : config.getDir())
            cnt += piecesSelect.get(d).size();
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nextPlayerFrist = -1;
        DonimoGame game = new DonimoGame(createDummyNamePlayers(2), 2, 7);
        System.out.println("Chon che do choi 15-25-50 :");
        int mode = 50;
        System.out.println("Ban da chon che do 15");

        while (true){
            //test
            System.out.println("-----------------------    New Game   -------------------------");
//            ReadFileJSON readFileJSON = new ReadFileJSON("hetpiece.json");
//            readFileJSON.getDatafile();
            if(nextPlayerFrist != -1) game.NextGame(nextPlayerFrist);
//            game.InitTest(readFileJSON.getList1(),readFileJSON.getList2());
            //test

            // Create a new game and start it
            game.init();
            l.logHandUser(game);
            // Main game loop

            if(nextPlayerFrist == -1) nextPlayerFrist = game.findPlayerPlayFirst();

            l.logPoint2Player(game);
            System.out.println("nextPlayerFrist :" + nextPlayerFrist);
//            game.findPlayerPlayFirst();
            while (true) {
                // xử lý game over
                if (game.isOver() == true) {
                    if(game.checkPlayerEmpty() == false){
                        game.SumPoint2Block();
                    }
                    System.out.println("Game Over");
                    break;
                }

                l.logCurrentPiece(game);
                l.logHandUser(game);

                // get curent Player
                int idxCurrentPlayer = game.curPlayer;
                DominoPlayer currentPlayer = game.listPlayers.get(idxCurrentPlayer);
                System.out.println(currentPlayer.name + "'s turn");

                // lay nhung la bai co the chon
                HashMap<String, List> piecesSelect = currentPlayer.getPieceCanSelect(game);
                if (cntPieceSelect(piecesSelect) == 0) {
                    System.out.println("Nguoi choi " + currentPlayer.name + " khong co bai nao co the chon nen bi block");
                    ++game.numPlayerBlock;
                    game.switchPlayer();
                    continue;
                }

                game.numPlayerBlock = 0;

                // Ask the current player to make a move

                System.out.println("Enter 'play' to play a piece or 'quit' to quit");
//                String input = scanner.nextLine();
                String input = "p";
                if (input.equals("p")) {
                    System.out.println("pieces can select: " + piecesSelect.toString());
                    int idxPieceSelect;
                    String dirSelect;
                    while (true) {
                        System.out.println("Enter '1' to play the piece at the left end, '2' to play it at the right end");
//                    dirSelect = scanner.nextInt() == 1 ? "left" : "right";

                        //test
                        dirSelect = "right";
                        if(piecesSelect.get("right").isEmpty()){
                            dirSelect = "left";
                        }
                        //test
                        List<Integer> tmp = piecesSelect.get(dirSelect);
                        System.out.print("Vi tri cac con bai co the chon neu choi ben " + dirSelect + ": ");
                        for (Integer i : tmp) {
                            System.out.print(i + " ");
                        }
                        while (true) {
                            System.out.println("\nChon vi tri bai muon danh hoc go -1 de chon huong khac: ");
//                        idxPieceSelect = scanner.nextInt();
                            //test
                            idxPieceSelect = tmp.get(0);
                            //test
                            if (idxPieceSelect == -1 || tmp.contains(idxPieceSelect))
                                break;
                        }
                        if (tmp.contains(idxPieceSelect))
                            break;
                    }
                    game.playPiece(currentPlayer, idxPieceSelect, dirSelect);
                    game.switchPlayer();
                } else if (input.equals("quit")) {
                    break;
                }
                System.out.println();
            }

            if(game.getWinner(mode)){
                break;
            }
            ++nextPlayerFrist;
            if(nextPlayerFrist >= game.listPlayers.size()){
                nextPlayerFrist = 0;
            }
        }

    }
}
