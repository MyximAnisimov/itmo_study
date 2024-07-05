package mypokemons;

import attacks.Iron_Defense;
import attacks.Thunderbolt;
import attacks.Vise_Grip;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Vikavolt extends Pokemon {
        public Vikavolt(String name, int level){
            super(name,level);
            super.setType(Type.BUG,Type.ELECTRIC);
            super.setStats(57,82,95,55,75,36);
            Vise_Grip vise_grip=new Vise_Grip(55,100);
            Thunderbolt thunderbolt=new Thunderbolt(90,100);
            Iron_Defense iron_defense=new Iron_Defense(0,0);
            super.setMove(vise_grip,thunderbolt, thunderbolt, iron_defense);}

        }
