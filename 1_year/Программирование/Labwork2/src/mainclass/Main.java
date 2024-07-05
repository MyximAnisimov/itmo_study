package mainclass;

import mypokemons.*;
import ru.ifmo.se.pokemon.Battle;

public class Main {
    public static void main(String[] args) {
        Battle b = new Battle();
       Nincada N1=new Nincada("Покемон1",3);
       Vikavolt V1=new Vikavolt("Покемон2",4);
        Charjabug C1=new Charjabug("Покемон3",1);
        Dedenne D1=new Dedenne("Покемон4",2);
        Grubbin G1=new Grubbin("Покемон5",6);
        Shedinja S1=new Shedinja("Покемон6",5);

        b.addAlly(N1);
        b.addAlly(C1);
        b.addAlly(G1);
        b.addFoe(V1);
        b.addFoe(S1);
        b.addFoe(D1);
        b.go();

    }
    public static boolean chance(double d) {
        return d>Math.random();
    }
}