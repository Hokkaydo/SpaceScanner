package be.hokkaydo.tpakami;

/**
 * Created by Hokkaydo on 29-04-2020.
 */
public class Main {
    public static void main(String[] args) {
        SpaceScanner spaceScanner = SpaceScanner.builder()
                .addRange(new RangeParameter(0, 5, 1))
                .addRange(new RangeParameter(0, 4, 2))
                .addAction(System.out::println)
                .addAction(System.err::println)
                .addFilter(list -> {
                    for(int i : list){
                        if(i % 2 != 0) return false;
                    }
                    return true;
                })
                .build();
        spaceScanner.run();
    }
}
