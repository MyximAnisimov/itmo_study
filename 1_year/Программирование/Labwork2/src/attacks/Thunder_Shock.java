package attacks;

import mainclass.Main;
import ru.ifmo.se.pokemon.*;
import ru.ifmo.se.pokemon.Type;

public class Thunder_Shock extends SpecialMove {
    public Thunder_Shock(double pow, double acc){
        super(Type.ELECTRIC, pow, acc);}
    @Override
    protected void applyOppDamage(Pokemon def, double damage){
        super.applyOppDamage(def, damage);
    }
@Override
        protected void applyOppEffects(Pokemon p){
    super.applyOppEffects(p);
if(Main.chance(0.1)){
    Effect.paralyze(p);}
        }
    @Override
    protected String describe(){
        return "использует Thunder Shock";
    }
    }

