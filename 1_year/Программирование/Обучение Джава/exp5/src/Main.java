import java.util.ArrayDeque;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayDeque<Integer> Arr1=new ArrayDeque<>();
        Scanner scan = new Scanner(System.in);
        int num =scan.nextInt();
        System.out.println(Arr1.add(num));
        System.out.println(Arr1);
    }
}