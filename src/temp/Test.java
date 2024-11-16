package temp;
import java.util.function.*;

public class Test {

    public static void main(String[] args) {

        // Consumer
        Consumer<String> loggingObject = (String val) -> {
            System.out.println("Logging "  + val);
        };
        loggingObject.accept("Aatman");

        Consumer<Integer> calculateBMI = (Integer val) -> {
            System.out.println("Age: " + val);
        };
        calculateBMI.accept(12);

        // Supplier
        Supplier<String> isEvenNumber = () -> "Yes It is!!";
        System.out.println(isEvenNumber.get());


        // Function
        Function<Integer, String> converToString = (Integer num) -> {
          return num.toString();
        };
        System.out.println(converToString.apply(111));

        // Predicate
        Predicate<Integer> isEvenNumber2 = (Integer num) -> {
            return num % 2 == 0;
        };
        System.out.println(isEvenNumber2.test(10));
        System.out.println(isEvenNumber2.test(11));
    }
}
