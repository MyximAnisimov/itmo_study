import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main extends Person{
    public static void main(String[] args) throws Exception{
        Person p1=new Person(){
            public void writename(){
                System.out.println(" loh");
            }
        };
        Person p2=new Person();
        Person p3 = new Person();
        System.out.println(p2.c);
        System.out.println(p3.c);
       Mathing m1= new Mathing(4,7);
        rename a=(String name)->{
           return name;
        };
        String name="Maxim";
        System.out.println(a.rename(name));
        math m=(int c,int d)->{
            System.out.println(c+d);
            return c+d;
        };
        Person per=new Person();
        math ms=per::calculate;
        ms.summ(6,7);
        Maxim M1=new Maxim("Maxim",18,175);
       Person.Misha M2=new Person.Misha();
       M2.setName("Mishsa");
       M2.changeName();
        System.out.println(p2.equals(p3));
       Maxim Refl=new Maxim("Maxim",18,175);
       Field[] f1=Maxim.class.getDeclaredFields();
       for(Field fld:f1){
           if(fld.getName().equals("name")){
               fld.setAccessible(true);
               fld.set(Refl,"Marina");
           }
       }
       System.out.println(Refl.getName());
Maxim Array= new Maxim(new int []{1,2,3,4},4);
Refl.Dividing(4,1);
try{
    Refl.ravno(M1);
}
catch(Exception e){
e.printStackTrace();
        }
Rocket R3= new Rocket("Union-1");
Rocket.Remote R1=R3.new Remote("Направо");
R1.changeDirect();
R1.Inf();
Rocket.Engine E1= new Rocket.Engine(false,1000);
E1.Losses();
R3.devices();

    new Rocket("Union-2"){
        @Override
        public void SentInf(){
            System.out.println("This is a twin of UNION-1 rjcket and its name is: "+this.getName());
        }
    }.SentInf();
String s="Строка";
Class c = s.getClass();
System.out.println(c);
Analyzer analyzer = new Analyzer();
analyzer.analyze(Refl);
            public void changeMethod(){
            Maxim M3 = null;
                try{
                    M3=new Maxim("David",17,180);
                Class cl=Class.forName(Maxim.class.getName());
                Method mthd=cl.getDeclaredMethod("summer");
                mthd.setAccessible(true);
                mthd.invoke(M3);}
             catch (ClassNotFoundException e) {
           e.printStackTrace();
       } catch (NoSuchMethodException e) {
           e.printStackTrace();
       } catch (IllegalAccessException e) {
           e.printStackTrace();
       } catch (InvocationTargetException e) {
           e.printStackTrace();
       }
            }
       changeMethod(); }
    }
