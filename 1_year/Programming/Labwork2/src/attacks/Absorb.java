package attacks;

import ru.ifmo.se.pokemon.*;

public class Absorb extends SpecialMove {
    public Absorb(double pow, double acc){
        super(Type.GRASS, pow,acc);
    }
    @Override
    protected void applyOppDamage(Pokemon def, double damage){
        super.applyOppDamage(def, damage);
    }
@Override
    protected void applyOppEffects(Pokemon p)
{super.applyOppEffects(p);
int rest_of_HP=(int) p.getHP();
int Full_HP=(int) p.getStat(Stat.HP);
Effect x= new Effect().stat(Stat.HP, rest_of_HP+(Full_HP - rest_of_HP)/2);
}
    @Override
    protected String describe(){
        return "использует Absorb ";
    }
}
