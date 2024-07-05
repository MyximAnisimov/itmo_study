package attacks;

import ru.ifmo.se.pokemon.*;

public class Eerie_Impulse extends StatusMove {
    public Eerie_Impulse(double pow, double acc) {
        super(Type.ELECTRIC, pow, acc);
    }
    @Override
    protected void applySelfEffects(Pokemon p)
    {super.applySelfEffects(p);
        Effect e = new Effect().stat(Stat.ATTACK,-2);
        p.addEffect(e);
    }

    @Override
    protected String describe(){
        return "использует Eerie_Impulse";
    }
}
