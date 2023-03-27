package vn.vietdefi.game.logic.domino;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DonimoGame {
    public List<DominoPlayer> listPlayers = new ArrayList<DominoPlayer>();
    public int curPlayer;
    public DominoSet set;
    // public DominoPiece currentPiece;
    public int numPlayer;
    public int numPieces;
    public int numPlayerBlock;
    public Config c;
    HashMap<String, Integer> currentPiece;

    public DonimoGame(String[] nameUsers, int numPlayer, int numPieces) {
        this.c = new Config();
        curPlayer = -1;
        currentPiece = new HashMap<String, Integer>();
        for (String d : c.getDir()) {
            currentPiece.put(d, -1);
        }
        this.numPlayer = numPlayer;
        this.numPieces = numPieces;
        for (String name : nameUsers) {
            DominoPlayer newPlayer = new DominoPlayer(name,0);
            listPlayers.add(newPlayer);
        }
    }

    public void NextGame(int nextPlayerFrist){
        currentPiece = new HashMap<String, Integer>();
        for (String d : c.getDir()) {
            currentPiece.put(d, -1);
        }
        curPlayer = nextPlayerFrist;
        numPlayerBlock = 0;
    }

    public boolean isCurrentPieceValid() {
        return currentPiece.get("left") != -1 && currentPiece.get("right") != -1;
    }

    public boolean isPlayFirst(int dominoVal, DominoPlayer player) {
        boolean isPlay = false;
        for (int i = 0; i < player.hand.size(); ++i) {
            DominoPiece piece = player.hand.get(i);
            int cntSameValue = 0;
            for (int val : piece.getValues())
                cntSameValue += val == dominoVal ? 1 : 0;
            if (cntSameValue < 2)
                continue;
            isPlay = true;
            break;
        }
        return isPlay;
    }

    public void switchPlayer() {
        ++curPlayer;
        if (curPlayer >= listPlayers.size())
            curPlayer = 0;
    }

    public int findPlayerPlayFirst() {
        for (int i = numPieces; i >= 0; --i) {
            for (int j = 0; j < listPlayers.size(); ++j) {
                DominoPlayer player = listPlayers.get(j);
                if (!isPlayFirst(i, player))
                    continue;
                curPlayer = j;
                break;
            }
            if (curPlayer != -1)
                break;
        }
        return curPlayer;
    }

    public void pickPieces(DominoPlayer player) {
        for (int i = 1; i <= this.numPieces; ++i)
            player.addPiece(set.RemotePiece(set.getLengthPieces() - 1));
    }
    public void pickPiecesTest(DominoPlayer player, List<Integer> num) {
        for (int i = 0; i < this.numPieces; ++i){
            for (int j = 0; j < set.getLengthPieces(); j++) {
                if(num.get(i) == j){
                    player.addPiece(set.getPieces().get(j));
                    break;
                }
            }
        }
    }

    public void init() {
        set = new DominoSet();
        // random chia bai
        DominoSet tmp;
        boolean canStart;
        set.shuffle();
        do {
            for (DominoPlayer player : listPlayers) {
                player.clearHand();
            }
            tmp = set;
            for (DominoPlayer player : listPlayers) {
                pickPieces(player);
            }
            canStart = !tmp.isFullDominoSameValue();
        } while (canStart == false);
        set = tmp;

//        findPlayerPlayFirst();
    }

    public void InitTest(List<Integer> num1,List<Integer> num2){
        set = new DominoSet();
//        DominoSet tmp;
//        boolean canStart;

//        do {
            for (DominoPlayer player : listPlayers) {
                player.clearHand();
            }
//            tmp = set;
            pickPiecesTest(listPlayers.get(0),num1);
            pickPiecesTest(listPlayers.get(1),num2);
//            canStart = !tmp.isFullDominoSameValue();
//        } while (canStart == false);
//        set = tmp;
//        findPlayerPlayFirst();
    }

    public boolean isOver() {
        // Check if either player has an empty hand
        int sum = 0;
        DominoPlayer winner = null;
        for(DominoPlayer player : listPlayers){
            if(player.isEmpty()) {
                winner = player;
            }else {
                sum = player.getSumPoint();
            }
            if(sum > 0 && winner != null){
                System.out.println(winner.name + "co diem "+ winner.point+" tang them " + sum + " "+10);
                winner.point = winner.point + sum + 10;

                return true;
            }
        }
        return numPlayerBlock >= numPlayer;
    }

    public void SumPoint2Block(){
        int point = 1000;
        int sum = 0;
        DominoPlayer winner = null;
            for (DominoPlayer player : listPlayers){
                if(player.getSumPoint() < point){  // 5 3
                    point = player.getSumPoint();
                    winner = player;
                }
                if(sum < player.getSumPoint()){
                    sum = player.getSumPoint();
                    System.out.println("sum = "+ sum);
                }
            }

            if(sum == point){
                int pointcheck = 0;
                sortPlayerHand();
                for (DominoPlayer player : listPlayers){
                    if(player.hand.get(0).getSumPiece() > pointcheck) {
                        pointcheck = player.hand.get(0).getSumPiece();
                        winner = player;
                    }
                }
            }
        System.out.println(winner.name + "co diem "+ winner.point+" tang them " + sum );
        winner.point += sum;
    }
    public boolean checkPlayerEmpty(){
        for (DominoPlayer player : listPlayers){
            if(player.isEmpty() == true) return true;
        }
        return false;
    }

    public void sortPlayerHand(){
        for (DominoPlayer player : listPlayers){
            player.SortPieceInHand();
        }
    }
    public void playPiece(DominoPlayer player, int idxPieceSelect, String d) {
        // Remove the piece from the player's hand and update the current piece
        DominoPiece pieceSelect = player.hand.get(idxPieceSelect);
        player.removePiece(idxPieceSelect);

        // nếu chưa có lá bài nào đc đanhs trên bàn thì gán cho curPiece chính là lá bài
        // vừa đánh
        if (!isCurrentPieceValid()) {
            currentPiece.put("left", pieceSelect.getValues().get(0));
            currentPiece.put("right", pieceSelect.getValues().get(1));
            return;
        }

        // ngược lại nếu có thi phải cập nhật gtri hướng vừa đanh của curPiece bằng gtri
        // ở hướng khác của lá bài vừa đánh
        Config c = new Config();
        int newVal = -1;
        for (int i = 0; i < c.getDir().size(); ++i) {
            if (c.getDir().get(i) != d)
                continue;
            for (Integer val : pieceSelect.getValues()) {
                if (val == currentPiece.get(d))
                    continue;
                newVal = val;
                break;
            }
            if (newVal != -1)
                break;
        }
        currentPiece.put(d, newVal);
    }

//    public void drawPiece(DominoPlayer player) {
//        // Draw a piece from the set and add it to the player's hand
//        player.addPiece(set.getAndRemovePiece());
//    }

    public boolean getWinner(int mode) {
        // Check which player has an empty hand and return the other player as the
        // winner
        DominoPlayer winner = null;
        for (DominoPlayer player : listPlayers) {
            if (player.point < mode) continue;
            winner = player;
            System.out.println("chuc mung nguoi choi " + winner.name+" da chien thang voi so diem " + winner.point);
            return true;
        }
        return false;
    }
}
