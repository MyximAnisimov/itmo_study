
public class Main {
    public static void main(String[] args) {
        Zebra obj_1=new Zebra("Maxim",12,"Dessert");
        if(obj_1.getAge()>10){
            System.out.println("Животное с именем"+obj_1.getName()+",живущее в "+obj_1.getlivingArea()+" маленькое");
        }
        else {
            System.out.println("Животное с именем "+obj_1.getName()+",живущее в "+obj_1.getlivingArea()+" большое");
        }
        Maxim obj_2=new Maxim(175,70,"Maxim");
        if(obj_2.getUsername()==obj_1.getName()){
            System.out.println("Животное и человек - одно существо :О");
        }
        else{
            System.out.println("Животное и человек - разные существа :|");
        }
    }
}