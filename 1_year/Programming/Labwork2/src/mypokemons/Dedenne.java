package mypokemons;

import attacks.Eerie_Impulse;
import attacks.Tail_Whip;
import attacks.Thunder_Shock;
import attacks.Thunder_Wave;
import ru.ifmo.se.pokemon.*;

public class Dedenne extends Pokemon {
    public Dedenne(String name, int level){
        super(name,level);
        super.setType(Type.ELECTRIC, Type.FAIRY);
        super.setStats(67, 58,57,81,67,101);
        Eerie_Impulse eerie_impulse=new Eerie_Impulse(0,100);
        Thunder_Wave thunder_wave=new Thunder_Wave(0, 90);
        Tail_Whip tail_whip =new Tail_Whip(0,100);
Thunder_Shock thunder_shock=new Thunder_Shock(40,100);

        super.setMove(eerie_impulse, thunder_wave, tail_whip, thunder_shock);
    }
}
