package characters;

import environment.items;

public class donut extends human {
    private String Floor;
    public donut(String name, int age, int id){
        super(name, age,id);
    }
    public void Move(String moves){
        System.out.println(moves);
    }
    @Override
    public void Forgetable(human Donut, boolean forget, human Dunno){
        if(forget){
            System.out.println(Donut.getName()+" забыл, где находится "+Dunno.getName());
        }
        else {
            System.out.println(Donut.getName()+" вспомнил, где находится "+Dunno.getName()+" и быстро нашёл его");
            System.exit(0);
        }
    }
    public void Identify(donut donut, dunno dunno) throws Exception{
        class IsIdDifferent extends Exception {
            public IsIdDifferent(String message) {
                super(message);
            }
        }
        if(donut.hashCode()==dunno.hashCode()){
            throw new IsIdDifferent(donut.getName()+" не может являться "+dunno.getName());
        }
    }
    public void setFloor(String Floor){
        this.Floor=Floor;
    }
    public String getFloor(){
        return Floor;
    }
    public void tryOpen(int Num_of_tries, items.Scafandr sc){
        for(int i=0;i<Num_of_tries;i++){
            if(sc.checkScafandr()){
                System.out.println("Пончик попытался открыть дверь в шлюзе"+'\n'+"Компьютер: *сканирует наличие скафандра*");
                System.out.println("Дверь открылась, Пончик в открытом космосе!");
                System.exit(0);
            }
            else{
                System.out.println("Пончик попытался открыть дверь в шлюзе"+'\n'+"Компьютер: *сканирует наличие скафандра*");
                System.out.println("Отказано в доступе!"+
                "Причина:"+ sc.getName()+" не надет");
            }
        }
    }
    public void tryEscapeCabin(int Num_tries){
        for(int i=0;i<Num_tries;i++){
            if(i==Num_tries-1){
                System.out.println(this.getName()+" наконец-то смог выбраться из кнопочной кабины ");
            }
            else{
                System.out.println(this.getName()+" пыатется выбраться из кнопочной кабины");
                System.out.println(this.getName()+" ударился об потолок из-за гравитации");
            }
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(getClass() == o.getClass())) {
            return false;
        }
        human human = (characters.human) o;
        return human.hashCode() == this.hashCode();
    }
    @Override
    public int hashCode(){
        return getID();
    }
}
