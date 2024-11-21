public class Person{
private int a;
private int b;
public static int c=1;

    private static String name;
    public static class Misha{
        public void changeName(){
            System.out.println(name+" loh");
        }
        public void setName(String name){
            Person.name=name;
        }
    }

    public void ClassName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
public int calculate(int a,int b){
        System.out.println(a+b);
        return a+b;
}
@Override
    public boolean equals(Object o){
        if(this==o){
            return true;
        }
        else if(o==null||!(getClass()==o.getClass())){
            return false;
        }
        Person p=(Person) o;
        return this.hashCode()==o.hashCode();
}
}
