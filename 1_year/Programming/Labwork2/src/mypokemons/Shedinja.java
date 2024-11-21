package mypokemons;

import attacks.Absorb;
import attacks.Facade;
import attacks.Scratch;
import attacks.Shadow_Sneak;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Shedinja extends Pokemon {
    public Shedinja(String name, int level){
        super(name, level);
        super.setType(Type.BUG, Type.GHOST);
        super.setStats(1,90,45,30,30,40);
        Absorb absorb=new Absorb(20,100);
        Scratch scratch = new Scratch(40, 100);
        Facade facade=new Facade(70,100);
Shadow_Sneak shadow_sneak=new Shadow_Sneak(40,100, 1);}}
