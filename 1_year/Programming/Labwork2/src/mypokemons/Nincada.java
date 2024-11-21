package mypokemons;

import attacks.Absorb;
import attacks.Facade;
import attacks.Scratch;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Nincada extends Pokemon {
    public Nincada(String name, int level){
        super(name, level);
super.setType(Type.BUG, Type.GROUND);
super.setStats(31,45,90,30,30,40);
Absorb absorb=new Absorb(20,100);
Scratch scratch = new Scratch(40, 100);
Facade facade = new Facade(70,100);
super.setMove(absorb,scratch,facade);
    }
}
