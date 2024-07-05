package attacks;

import ru.ifmo.se.pokemon.PhysicalMove;
import ru.ifmo.se.pokemon.Pokemon;
import ru.ifmo.se.pokemon.Status;
import ru.ifmo.se.pokemon.Type;

public class Facade extends PhysicalMove {
    public Facade(double pow, double acc){
        super(Type.NORMAL,pow, acc);
    }
    private boolean Poisoned=false;
    private boolean Burned=false;
    private boolean Paralyzed=false;
    @Override
    protected void applyOppDamage(Pokemon p, double damage){
        Status conditions=p.getCondition();
                Poisoned = conditions.equals(Status.POISON);
                Burned=conditions.equals(Status.BURN);
                Paralyzed=conditions.equals(Status.PARALYZE);
                if(Poisoned||Burned||Paralyzed){
                    super.applyOppDamage(p, damage);
                    super.applyOppDamage(p, damage);
                }

    }
    @Override
    protected String describe(){
        return "использует Facade";
    }
}
