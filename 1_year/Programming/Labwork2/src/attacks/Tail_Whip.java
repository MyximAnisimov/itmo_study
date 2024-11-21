package attacks;

import ru.ifmo.se.pokemon.*;

public class Tail_Whip extends StatusMove {
    public Tail_Whip(double pow, double acc)
    {
        super(Type.NORMAL,pow, acc);
    }
    @Override
    protected void applyOppEffects(Pokemon p)
    {super.applyOppEffects(p);
        Effect e = new Effect().stat(Stat.ATTACK,-1);
        p.addEffect(e);
    }
    @Override
    protected String describe(){
        return "использует Tail Whip ";
    }
}
