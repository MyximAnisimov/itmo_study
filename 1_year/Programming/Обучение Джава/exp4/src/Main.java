import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void Analyzer() {
        human Maxim = null;
        Class cl = null;
        try {
            Maxim = new human("Maxim",false);
            cl = Class.forName(human.class.getName());
            Field[] changeName = cl.getDeclaredFields();
            Method mthd=cl.getDeclaredMethod("changeName");
            Field dls=cl.getDeclaredField("ischange");
            dls.setAccessible(true);
            mthd.setAccessible(true);
            System.out.println(mthd.invoke(Maxim));
for(int i=0;i< changeName.length;i++){
    System.out.println("Поле "+i+": "+changeName[i]+'\n'+
            "Класс: "+changeName.getClass());
}
        } catch (Throwable e){
            System.err.println(e);
        }
    }
    public static void Analyze2(){
        human Misha=new human("Misha",false);
        Class cl2=Misha.getClass();
        Field[] flds=cl2.getDeclaredFields();
        for(int i=0;i<flds.length;i++){
            System.out.println("Поле: "+flds[i]);
        }
    }
    public static void main(String[] args) {
       Analyze2();
        human Misha=new human("Misha", false);
        Misha.Generics(Misha.getName(),Misha.getClass());
}}