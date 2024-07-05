package attacks;

import ru.ifmo.se.pokemon.*;

public class Iron_Defense extends StatusMove {
    public Iron_Defense(double pow, double acc){
        super(Type.STEEL,pow,acc);
    }
    @Override
    protected void applySelfEffects(Pokemon p)
    {super.applySelfEffects(p);
        Effect e = new Effect().stat(Stat.ATTACK,2);
        p.addEffect(e);
    }
    @Override
    protected String describe(){
        return "использует Iron_Defense";
    }
}
