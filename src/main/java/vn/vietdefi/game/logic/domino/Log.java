package vn.vietdefi.game.logic.domino;

import java.util.HashMap;
import java.util.List;

public class Log {
    Config c = new Config();

    public void logInfoPieceCanSelect(HashMap<String, List> piecesSelect) {
        for (String d : c.dir) {
            String s = "Piece " + d;
            List<Integer> tmp = piecesSelect.get(d);
            for (Integer i : tmp)
                s += ": " + i;
            System.out.println(s);
        }
    }

    public void logHandUser(DonimoGame game) {
        for (DominoPlayer player : game.listPlayers) {
            System.out.println(player.name + "'s hand: " + player.hand);
        }
    }

    public void logCurrentPiece(DonimoGame game) {
        if (!game.isCurrentPieceValid())
            return;
        String s = "";
        for (String d : c.dir) {
            s += d + " : " + game.currentPiece.get(d) + " ";
        }
        System.out.println(s);
    }

    public void logPoint2Player(DonimoGame game){
        System.out.println();
        for (DominoPlayer player : game.listPlayers){
            System.out.println("Diem cua " + player.name + " la : " + player.point);
        }
        System.out.println();
    }
}
