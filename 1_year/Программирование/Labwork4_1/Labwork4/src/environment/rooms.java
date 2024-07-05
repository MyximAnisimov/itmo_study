package environment;

import characters.crowd;
import characters.human;

import static java.lang.Math.random;

public class rooms implements searchable {
    private crowd[]people=new crowd[crowd.getNum_people()];
    @Override
    public void Searchable(human Donut, boolean searching, int Donut_Energy, human Dunno, crowd[]people)throws RuntimeException{
        class InCorrectNumber extends RuntimeException {
            public InCorrectNumber(String message) {
                super(message);
            }
        }
        int Final_energy1 = Donut_Energy;
        if(Final_energy1>100||Final_energy1<10){

            throw new InCorrectNumber("Введено некорректное значение энергии Пончика");}

        for(int i = 0; i< crowd.getNum_people(); i++){

            this.people[i]=people[i];
        }
        int rand;
        if (searching) {
            for(int i=0;i<people.length;i++){

                if(Final_energy1>=10){

                    Final_energy1 = Final_energy1 - 20;

                    rand = (int) (3 * random() + 1);

                    if (rand==1) {

                        System.out.println(Donut.getName() + " пошёл на " + floors.FIRST_FLOOR.getFloors());

                    } else if (rand==2)  {

                        System.out.println(Donut.getName() + " пошёл на " + floors.SECOND_FLOOR.getFloors());

                    } else if (rand==3)  {

                        System.out.println(Donut.getName() + " пошёл на " + floors.THIRD_FLOOR.getFloors());
                    }

                    System.out.println(Donut.getName() + " пытается найти " + Dunno.getName());

                    if(Dunno.hashCode()==people[i].hashCode()){
                        System.out.println(Dunno.getName()+" найден");
                        System.exit(0);
                    }
                    else{
                        System.out.println(Dunno.getName()+" не найден");}
                    System.out.println(Donut.getName() + " теряет энергию");
                }}
            if (Final_energy1 <10) {
                System.out.println(time.LATE_TIME.getTime());
                System.out.println(Donut.getName() + " валится с ног от усталости");
                System.out.println(Donut.getName() + " заснул");
                System.out.println(Dunno.getName() + " проснулся в пищевом отсеке");

            }
        } else {
            System.out.println(Donut.getName() + " не знает, что делать");
        }

    }}

