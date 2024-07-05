import characters.*;
import environment.*;
import jdk.jfr.AnnotationElement;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnnotation {
}

class C1 {

    @MyAnnotation
    private String f1;
    private String f2;
    @MyAnnotation
    private String f3;


}
public class Main {

    static void reflection(Class a) {
        Field[] flds = a.getDeclaredFields();
        for(Field fld  : flds)
        {
            if (fld.isAnnotationPresent(MyAnnotation.class))
            {
               System.out.println(fld.getName());
               System.out.println(Arrays.toString(fld.getAnnotations()));
            }
        }}

    public static void main(String[] args) {
        donut donut = new donut("Пончик",17,12);
        dunno dunno = new dunno("Незнайка", 17, 11);
        crowd cr1=new crowd(1);
        crowd cr2=new crowd(2);
        crowd cr3=new crowd(3);
        crowd cr4=new crowd(4);
        rooms room=new rooms();
        items.Scafandr sc=new items().new Scafandr("Scafandr-1",false);
        world world = new world(time.EARLY_TIME.getTime());
        try{
            donut.Identify(donut,dunno);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        donut.Move(donut.getName()+" решил выбраться из кнопочной кабины");
        donut.tryEscapeCabin(2);
        new rocket(){
            @Override
            public void OnFloor(characters.donut donut){
                donut.setFloor(floors.FIRST_FLOOR.getFloors());
            }
        }.OnFloor(donut);
        donut.Move(donut.getName()+" спустился на "+donut.getFloor());
        donut.tryOpen(2,sc);
        donut.Move(donut.getName()+" решил вернуться к "+dunno.getName());
        donut.Forgetable(donut,true,dunno);
        room.Searchable(donut,true,80,dunno,new crowd[]{cr1,cr2,cr3,cr4});
        world.Sensable(dunno,true,false);
        dunno.Thoughts("\"Значит, пока я спал, Знайка и остальные коротышки погрузились на корабль и отправились на Луну. Всё получилось точно, как я рассчитал!\" -- подумал "+dunno.getName());
        donut donut2=new donut("Donut2",19,453);
        rocket.Engine Eng1=new rocket.Engine(true,60);
        try{
            Eng1.check();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        reflection(C1.class);
    }
}
