package vn.vietdefi.game.logic.domino;

import java.util.ArrayList;
import java.util.List;

public class Config {
    List<String> dir;

    public Config() {
        dir = new ArrayList<String>();
        dir.add("left");
        dir.add("right");
    }

    public List<String> getDir() {
        return dir;
    }
}
