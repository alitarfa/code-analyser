import java.util.Comparator;
import java.util.stream.Stream;

public class Main {

    public static void main(String args[]) {
        Stream<Integer> integerStream = Stream.of(3, 2, 5, 8, 88, 0);
       integerStream.sorted(Comparator.comparingInt(o -> o))
               .forEach(System.out::println);

    }
}
