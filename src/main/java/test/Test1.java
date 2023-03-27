package test;

import vn.vietdefi.game.logic.domino.DominoPlayer;
import vn.vietdefi.game.logic.domino.DonimoGame;

public class Test1 {

    public static String[] createDummyNamePlayers(int sl) {
        String[] namePlayers = new String[sl];
        for (int i = 0; i < namePlayers.length; i++) {
            namePlayers[i] = "Player " + (i + 1);
        }
        return namePlayers;
    }
    public static void main(String[] args) {
        DonimoGame game = new DonimoGame(createDummyNamePlayers(2),2,7);
        game.init();
        game.sortPlayerHand();
        for (DominoPlayer player : game.listPlayers){
            player.getFullHand();
            System.out.println();
        }
    }
}
