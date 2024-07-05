
package attacks;
import ru.ifmo.se.pokemon.*;
public class Thunder_Wave extends StatusMove {
    public Thunder_Wave(double pow, double acc){
        super(Type.ELECTRIC, pow, acc);}
    @Override
    protected void applyOppEffects(Pokemon p){
        super.applyOppEffects(p);
    int Speed_value=(int) p.getStat(Stat.SPEED);
    Effect a = new Effect().attack(0.75).stat(Stat.SPEED, Speed_value/2 );
    p.addEffect(a);}
    @Override
    protected String describe() {
        return "использует Thunder Wave";

    }

    }

