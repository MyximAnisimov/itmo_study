import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Analyzer {
    public void analyze(Object o){
        Class cr=o.getClass();
        Method[]mthds=cr.getDeclaredMethods();
        Field[]flds=cr.getDeclaredFields();
        for(Method mthd:mthds){
            System.out.println("Метод :"+" "+mthd);
        }}
}
