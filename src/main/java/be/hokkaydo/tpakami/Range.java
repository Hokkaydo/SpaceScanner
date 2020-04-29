package be.hokkaydo.tpakami;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by Hokkaydo on 29-04-2020.
 */
public class Range {

    private final RangeParameter rangeParameter;
    private final int id;

    private int current;
    private final int step;

    private Range childRange = null;
    private final Range parentRange;

    public RangeParameter getRangeParameter() {
        return rangeParameter;
    }

    public Range getChildRange() {
        return childRange;
    }

    public Range getParentRange() {
        return parentRange;
    }

    Range(int id, Range parentRange, RangeParameter rangeParameter, List<RangeParameter> rangeParameters){
        this.id = id;
        this.step = rangeParameter.getStep();
        this.parentRange = parentRange;
        this.rangeParameter = rangeParameter;
        this.current = rangeParameter.getStart();
        if(!rangeParameters.isEmpty()) {
            this.childRange = new Range(++id, this, rangeParameters.remove(0), rangeParameters);
        }
    }

    public void makeOneLoop(Integer[] integers, List<Predicate<List<Integer>>> filters, List<Consumer<List<Integer>>> actions){
        integers[id] = current;
        if(isAtEnd()){
            if(this.parentRange == null) return;
            this.parentRange.incrementCurrent();
            this.current = rangeParameter.getStart();
            this.parentRange.makeOneLoop(integers, filters, actions);
        }else{
            if(hasChild()){
                this.childRange.makeOneLoop(integers, filters, actions);
            }else{
                boolean cancel = false;
                for (Predicate<List<Integer>> filter : filters) {
                    if(!filter.test(Arrays.asList(integers))) {
                        cancel = true;
                        break;
                    }
                }
                if(!cancel) actions.forEach(a -> a.accept(Arrays.asList(integers)));
                incrementCurrent();
                makeOneLoop(integers, filters, actions);
            }
        }
    }

    public void incrementCurrent(){
        current += step;
    }
    public boolean isAtEnd(){
        return current + step == rangeParameter.getEnd();
    }
    public boolean hasChild(){
        return this.childRange != null;
    }
}
