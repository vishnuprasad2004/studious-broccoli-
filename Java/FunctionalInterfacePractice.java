import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


interface ShapeCalc extends BiFunction<Double, Double, Double> {
    Double apply(Double a, Double b);
    Double PI = 3.14159;
}

public class FunctionalInterfacePractice {
    public static void main(String[] args) {
        Consumer<Integer> print = System.out::println;


        Function<Integer, Integer> squared = (a) -> (int) Math.pow(a,2);

        BiFunction<Integer, Integer, Integer> add = (a, b) -> a+b;

        Predicate<Integer> isEven = (a) -> a%2==0;
        Predicate<Integer> isMultipleOf3  = a -> a % 3 == 0;
        int localVar = 32;

        Supplier<Boolean> randomBooleans = new Supplier<>() {
            @Override
            public Boolean get() {
                Random r = new Random();
                return r.nextBoolean();
            }
        };

        Supplier<Integer> generateRandomNo = () -> {
            Random r = new Random();
            return r.nextInt(10,20);
        };


        ShapeCalc cylinderCsa = (r,h) -> ShapeCalc.PI * 2 * r * h;
        ShapeCalc cylinderTsa = (r, h) -> 2*ShapeCalc.PI*r*(r+h); // 2PIr^2 + 2PIrh = 2PIr(r+h)
        ShapeCalc coneCsa = (r,l) -> ShapeCalc.PI*r*(r+l); // πr(r + l)

        System.out.println(squared.apply(100));
        System.out.println(add.apply(12,12));
        System.out.println(isEven.test(1232));
        System.out.println(generateRandomNo.get());

//    System.out.println(a.apply(10.0,10.0));

        ArrayList<Integer> arr = new ArrayList<>(List.of(1,2,3,4,5,6,7));

        Stream<Integer> s1 = arr.stream();
        Stream<Integer> s2 = s1.filter(n -> n%2==0);
        System.out.println(s2.collect(Collectors.toSet()));
        System.out.println(Function.identity());
        System.out.println(isEven.and(isMultipleOf3).test(12)); // true
        System.out.println(isEven.and(isMultipleOf3).test(13)); // false
        System.out.println(isEven.or(isMultipleOf3).test(4)); // true
        String result = arr.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(", "));
        System.out.println(result);
    }
}
