package vn.vietdefi.game.logic.domino;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class DominoPlayer {
    public String name;
    public int point;
    public List<DominoPiece> hand;

    public void clearHand() {
        this.hand.clear();
    }

    public DominoPlayer(String name, int point) {
        this.name = name;
        this.point = point;
        hand = new ArrayList<DominoPiece>();
    }

    public int getSumPoint() {
        int sum = 0;
        for (DominoPiece piece : hand) {
            sum += piece.getSumPiece();
        }
        return sum;
    }

    public void SortPieceInHand() {
        List<Integer> tg;
        for (int i = 0; i < hand.size() - 1; i++) {
            for (int j = i + 1; j < hand.size(); j++) {
                if (hand.get(i).getSumPiece() < hand.get(j).getSumPiece()) {
                    // Hoan vi 2 so a[i] va a[j]
                    tg = hand.get(i).values;
                    hand.get(i).values = hand.get(j).values;
                    hand.get(j).values = tg;
                }
            }
        }
    }

    public void getFullHand(){
        for (DominoPiece p : hand){
            System.out.print(p.toString()+" ,");
        }
    }


    public HashMap<String, List> getPieceCanSelect(DonimoGame game) {
        HashMap<String, List> piecesSelect = new HashMap<String, List>();
        List<Integer> defaultValue = new ArrayList<Integer>();
        boolean isCurrentPieceInvalid = !game.isCurrentPieceValid();
        if (isCurrentPieceInvalid) {
            for (int i = 0; i < hand.size(); i++)
                defaultValue.add(i);
        }

        Config c = new Config();
        for (String d : c.getDir()) {
            piecesSelect.put(d, new ArrayList<Integer>(defaultValue));
        }
        if (isCurrentPieceInvalid)
            return piecesSelect;

        HashMap<String, Integer> curPiece = game.currentPiece;
        for (int i = 0; i < hand.size(); i++) {
            DominoPiece piece = hand.get(i);
            HashSet<Integer> set = new HashSet<Integer>(piece.getValues());
            for (Integer val : set) {
                for (String d : c.getDir()) {
                    if (val != curPiece.get(d))
                        continue;
                    piecesSelect.get(d).add(i);
                }
            }
        }
        return piecesSelect;
    }

    public void addPiece(DominoPiece piece) {
        hand.add(piece);
    }

    public DominoPiece removePiece(int index) {
        return hand.remove(index);
    }

    public boolean hasPiece(DominoPiece piece) {
        return hand.contains(piece);
    }

    public boolean isEmpty() {
        return hand.isEmpty();
    }

    //getter setter
}
