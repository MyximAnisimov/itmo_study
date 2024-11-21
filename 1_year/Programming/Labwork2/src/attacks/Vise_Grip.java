package attacks;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Vise_Grip extends PhysicalMove {
    public Vise_Grip(double pow, double acc){
        super(Type.NORMAL,pow,acc);}
        @Override
        protected void applyOppDamage(Pokemon p, double damage){
            super.applyOppDamage(p, damage);
        }
    @Override
    protected String describe(){
        return "использует Vise Grip";
    }
    }

