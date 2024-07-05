package environment;

import characters.*;

import static java.lang.Math.random;

public class Rooms extends Places implements Searchable {
    private boolean Gravity;
public Rooms(boolean Gravity) {
   this.Gravity=Gravity;
}

    public void Searchable(Human Donut, boolean searching, int Donut_Energy, Human Dunno, Human.humanArray people) {

        int Final_energy1 = Donut_Energy;

        if(Final_energy1>100||Final_energy1<10){

            System.out.println("Пожалуйста, введите значение энергии Пончика от 10 до 100");

        System.exit(0);}
int rand;
        if (searching) {
                for(int i=0;i<people.getNum_people();i++){

                    if(Final_energy1>=10){

                        Final_energy1 = Final_energy1 - 10;

                rand = (int) (3 * random() + 1);

                if (rand==1) {

                    System.out.println(Donut.getName() + " пошёл на " + Floors.FIRST_FLOOR.getFloors());

                } else if (rand==2)  {

                    System.out.println(Donut.getName() + " пошёл на " + Floors.SECOND_FLOOR.getFloors());

                } else if (rand==3)  {

                    System.out.println(Donut.getName() + " пошёл на " + Floors.THIRD_FLOOR.getFloors());
                }

                System.out.println(Donut.getName() + " пытается найти " + Dunno.getName());

                if(Dunno.hashCode()==people.hashCode()){
                    System.out.println(Dunno.getName()+" найден");
                    System.exit(0);
                }
                else{
                    System.out.println(Dunno.getName()+" не найден");}
                System.out.println(Donut.getName() + " теряет энергию");
                }}
                if (Final_energy1 <10) {
                    System.out.println(Time.LATE_TIME.getTime());
                    System.out.println(Donut.getName() + " валится с ног от усталости");
                    System.out.println(Donut.getName() + " заснул");
                    System.out.println(Dunno.getName() + " проснулся в пищевом отсеке");

                }
        } else {
            System.out.println(Donut.getName() + " не знает, что делать");
        }

    }
}
