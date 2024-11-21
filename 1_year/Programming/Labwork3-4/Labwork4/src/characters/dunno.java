package characters;

public class dunno extends human {
    public dunno(String name, int age, int id){
        super(name,age,id);
    }
    public void Thoughts(String thought){
        System.out.println(thought);
    }
    @Override
    public void Forgetable(human Donut,boolean forget, human Dunno){
        if(forget){
            System.out.println(Donut.getName()+" забыл, где находится "+Dunno.getName());
        }
        else {
            System.out.println(Donut.getName()+" вспомнил, где находится "+Dunno.getName()+" и быстро нашёл его");
            System.exit(0);
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
        human human = (characters.human) o;
        return human.getID() == this.getID();
    }
    @Override
    public int hashCode(){
        return getID();
    }
}

