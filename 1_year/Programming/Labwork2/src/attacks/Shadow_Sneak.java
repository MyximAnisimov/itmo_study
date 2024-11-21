package attacks;

import ru.ifmo.se.pokemon.*;

public class Shadow_Sneak extends PhysicalMove {
    public Shadow_Sneak(double pow, double acc, int priority){
        super(Type.GHOST, pow, acc);}
    @Override
    protected void applyOppDamage(Pokemon p, double d)
    {
        super.applyOppDamage(p, d);
    }
    protected String describe(){
        return "использует Shadow Sneak";
    }
}
