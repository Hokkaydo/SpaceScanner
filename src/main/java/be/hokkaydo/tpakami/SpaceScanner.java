package be.hokkaydo.tpakami;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by Hokkaydo on 29-04-2020.
 */
public class SpaceScanner {

    private final List<Consumer<List<Integer>>> actions;
    private final List<Predicate<List<Integer>>> filters;
    private final List<RangeParameter> ranges;

    private final int rangesSize;

    public void run(){
        Range firstRange = new Range(0, null, ranges.remove(0), ranges);
        firstRange.makeOneLoop(new Integer[rangesSize], filters, actions);
    }

    private SpaceScanner(List<Consumer<List<Integer>>> actions, List<Predicate<List<Integer>>> filters, List<RangeParameter> ranges){
        this.actions = actions;
        this.filters = filters;
        this.ranges = ranges;
        this.rangesSize = ranges.size();
    }

    public static Builder builder(){
        return new Builder();
    }

    static class Builder{
        private final List<Consumer<List<Integer>>> actions = new ArrayList<>();
        private final List<Predicate<List<Integer>>> filters = new ArrayList<>();
        private final List<RangeParameter> ranges = new ArrayList<>();

        public Builder addRange(RangeParameter rangeParameter) {
            this.ranges.add(rangeParameter);
            return this;
        }
        public Builder addAction(Consumer<List<Integer>> action){
            this.actions.add(action);
            return this;
        }
        public Builder addFilter(Predicate<List<Integer>> filter){
            this.filters.add(filter);
            return this;
        }
        public SpaceScanner build(){
            return new SpaceScanner(actions, filters, ranges);
        }
    }
}
