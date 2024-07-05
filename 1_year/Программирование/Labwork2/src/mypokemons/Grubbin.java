package mypokemons;

import attacks.Thunderbolt;
import attacks.Vise_Grip;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Grubbin extends Pokemon {
public Grubbin(String name, int level){
    super(name, level);
    super.setType(Type.BUG);
    super.setStats(47, 62,45,55,45,46);
Vise_Grip vice_grip=new Vise_Grip(55,100);
Thunderbolt thunderbolt=new Thunderbolt(90, 100);
super.setMove(vice_grip,thunderbolt);
}
}
