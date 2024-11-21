package attacks;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Type;

public class Scratch extends PhysicalMove {
    public Scratch(double pow, double acc){
        super(Type.NORMAL, pow, acc);}
        @Override
        protected void applyOppDamage(Pokemon p, double d){
            super.applyOppDamage(p, d);
    }
    @Override
    protected String describe(){
        return "использует Scratch";
    }
}
