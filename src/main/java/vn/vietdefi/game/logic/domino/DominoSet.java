package vn.vietdefi.game.logic.domino;

import java.util.ArrayList;
import java.util.Collections;

public class DominoSet {
    private ArrayList<DominoPiece> pieces;
    private int n = 6;


    public DominoSet() {
        pieces = new ArrayList<>();
        // Create all possible domino pieces and add them to the set
        for (int i = 0; i <= n; i++) {
            for (int j = i; j <= n; j++) {
                pieces.add(new DominoPiece(i, j));
            }
        }
        // System.out.println(pieces.toString());
    }

    public void getPieceFull(){
        System.out.println(this.pieces);
        System.out.println(this.pieces.size());
        for (int i = 0; i < pieces.size(); i++) {
            System.out.print(i + ": ");
            System.out.println(pieces.get(i).toString());
        }
    }
    public boolean isFullDominoSameValue() {
        int cnt = 0;
        for (int i = 0; i < pieces.size(); ++i) {
            DominoPiece piece = pieces.get(i);
            cnt += piece.hasSameVal() ? 1 : 0;
        }
        return cnt >= 6;
    }

    public void shuffle() {
        // Shuffle the pieces randomly
        Collections.shuffle(pieces);
    }

//    public DominoPiece getAndRemovePiece() {
//        // Remove a random piece from the set and return it
//        return pieces.remove((int) (Math.random() * pieces.size()));
//    }

    public DominoPiece RemotePiece(int i){
        return pieces.remove(i);
    }

    public ArrayList<DominoPiece> getPieces() {
        return pieces;
    }

    public void setPieces(ArrayList<DominoPiece> pieces) {
        this.pieces = pieces;
    }

    public int getLengthPieces() {
        return this.pieces.size();
    }

    public DominoPiece remotepiece(int i){
        return pieces.remove(i);
    }


}
