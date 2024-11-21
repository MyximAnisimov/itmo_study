import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
         TreeSet list = new TreeSet();
         Object num[] = new Object[10];
         list.add(1);
         list.add(2);
         list.add(2);
        System.out.print(list.size()+", num= [");
         for(int i=0;i<list.size()+1;i++){
             num[i]=list.pollFirst();
             System.out.print(num[i]+", ");
         }
        System.out.print(" ]");

    }
}