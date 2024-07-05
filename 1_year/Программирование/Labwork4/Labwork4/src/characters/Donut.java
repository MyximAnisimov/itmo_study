package characters;

import environment.Floors;

public class Donut extends Human{
    public Donut(String name,int age,int id){
        super(name, age,id);
    }
    public void Thoughts(String thoughts){
        System.out.println(thoughts);
    }
    public void Forgetable(Human Donut,boolean forget,Human Dunno){
        if(forget){
            System.out.println(Donut.getName()+" забыл, где находится "+Dunno.getName());
        }
        else {
            System.out.println(Donut.getName()+" вспомнил, где находится "+Dunno.getName()+" и быстро нашёл его");
            System.exit(0);
        }
    }
    public void Identify(Donut donut,Dunno dunno) throws RuntimeException{
        class IsIdIndividual extends RuntimeException{
public IsIdIndividual(String message){
    super(message);
    if(donut.hashCode()==dunno.hashCode()){
        throw new IsIdIndividual(donut.getName()+" не может являться "+dunno.getName());
}}
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() == o.getClass()) {
            return false;
        }
        Human human = (Human) o;
        return human.getID() == this.getID();
    }
    @Override
    public int hashCode(){
        return getID();
    }


}
