package vn.vietdefi.game.logic.domino;

import java.util.ArrayList;
import java.util.List;

public class DominoPiece {
    List<Integer> values = new ArrayList<Integer>();

    public DominoPiece(int value1, int value2) {
        values.add(value1);
        values.add(value2);
    }

    public String toString() {
        String s = "";
        for (Integer val : values)
            s += val.toString() + " ";
        return s;
    }

    public int getSumPiece(){
        return values.get(0) + values.get(1);
    }

    public boolean hasSameVal() {
        return values.get(0) == values.get(1);
    }

    public List<Integer> getValues() {
        return values;
    }
    public Integer getValue1() {
        return values.get(0);
    }
    public Integer getValue2() {
        return values.get(1);
    }

    public void setValues(List<Integer> values) {
        this.values = values;
    }
}
