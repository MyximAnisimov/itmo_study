package attacks;

import mainclass.Main;
import ru.ifmo.se.pokemon.*;

public class Thunderbolt extends SpecialMove {
    public Thunderbolt(double pow, double acc){
        super(Type.ELECTRIC, pow, acc);
    }
    @Override
    protected void applyOppDamage(Pokemon def, double damage){
        super.applyOppDamage(def, damage);
    }
    @Override
    protected void applyOppEffects(Pokemon p)
    {super.applyOppEffects(p);
      if(Main.chance(0.1))
      {Effect.paralyze(p);
    }}
    @Override
    protected String describe(){
        return "использует Thunderbolt";
    }}

