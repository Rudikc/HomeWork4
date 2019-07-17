import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamUtils {


    //    Task1


    public static String task01(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        AtomicInteger i = new AtomicInteger(1);
        list.forEach(l -> {
                    stringBuilder.append(i + ". " + l + ", ");
                    i.addAndGet(2);
                }
        );

        stringBuilder.delete(stringBuilder.toString().length() - 2, stringBuilder.toString().length());
        return stringBuilder.toString();
    }


    public static String task012(List<String> list) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 1;
        for (String name : list) {
            stringBuilder.append(i + ". " + name + ", ");
            i += 2;
        }
        stringBuilder.delete(stringBuilder.toString().length() - 2, stringBuilder.toString().length());
        return stringBuilder.toString();
    }

    //    Task2
    public static List<String> task02(List<String> list) {
        return list.stream().map(String::toUpperCase).peek(System.out::println).sorted().collect(Collectors.toList());
    }

    //    Task3
    public static List<String> task03(List<String> list) {
        return Arrays.asList(list.stream().flatMap((p) -> Arrays.stream(p.split(","))).toArray(String[]::new));

    }

    //    Task4
    public static IntStream task04(int seed, int a, int c, int m) {
        return IntStream.iterate(seed, x -> (a * x + c) % m);
    }
    //            𝑥[𝑛+1]=(𝑎𝑥[𝑛]+𝑐)%𝑚

    public static <T> Stream<T> task05(Stream str1, Stream str2) {
        AtomicInteger firstElement = new AtomicInteger(-1);
        AtomicInteger secondElement = new AtomicInteger(-1);

        AtomicInteger str1Length = new AtomicInteger((int) str1.count());
        AtomicInteger str2Length = new AtomicInteger((int) str2.count());

        Stream resultStream = Stream.generate(() -> {
            if (firstElement.get() == secondElement.get() && firstElement.get() < str1Length.get() - 1) {
                firstElement.getAndIncrement();
                return str1.skip(firstElement.get()).findFirst();
            } else if (secondElement.get() < str2Length.get() - 1) {
                secondElement.getAndIncrement();
                return str2.skip(secondElement.get()).findFirst();
            }else return Optional.empty();
        });

        return resultStream;
    }

    public static void main(String[] args) {
    }

}
