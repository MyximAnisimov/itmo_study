public class human {
    private String name;
    private int age;
    private int height;
    private int weight;
    private boolean ischange;
    public human(String name, boolean ischange){
        this.name=name;
        this.ischange=ischange;
    }

    public <T,U> void  Generics(T a, U b){
      System.out.println("1: "+a+" "+" 2: "+b);
    }

    public String getName(){
        return name;
    }
    private void changeName(){
        System.out.println(getName()+" - какое красивое имя");
    }
    private void isc(){
        if(ischange){
            changeName();
        }
    }
}
