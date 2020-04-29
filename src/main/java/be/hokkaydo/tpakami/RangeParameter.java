package be.hokkaydo.tpakami;

/**
 * Created by Hokkaydo on 29-04-2020.
 */
public class RangeParameter {
    private final int start;
    private final int end;

    private final int step;

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public int getStep(){
        return step;
    }

    RangeParameter(int start, int end, int step){
        this.start = start;
        this.end = end;
        this.step = step;
    }
}
