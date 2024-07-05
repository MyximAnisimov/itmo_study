package characters;

public class Dunno extends Human{
    public Dunno(String name,int age,int id){
        super(name,age,id);
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
        }}
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

