import javax.print.attribute.IntegerSyntax;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("january");
        Person p2 = new Person("february");
        Person p3 = new Person("march");
        Person p4 = new Person("april");
        Person p5 = new Person("may");
        Person p6 = new Person("june");
        Stream.of(p1, p2, p3 ,p4, p5, p6)
                .filter(s-> s.getName().length()<=7)
                .map(s-> Person.delLast(s.getName()))
                .skip(2)
                .sorted()
                .forEachOrdered(System.out::print);
    };
}