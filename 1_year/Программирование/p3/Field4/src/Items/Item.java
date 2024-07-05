package Items;

public abstract class Item {
    float length;
    float height;
    float width;
    int energy;
    int id;
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }


    public Item(float length , float height , float width){
        this.length = length;
        this.height = height;
        this.width = width;
    }

    public Item(float length , float height , float width,int energy,int id){
        this.length = length;
        this.height = height;
        this.width = width;
        this.energy = energy;
        this.id = id;
    }

    public Item(float length , float height , float width, Magnet magnet, MoonRock moonrock){
        this.length = length;
        this.height = height;
        this.width = width;

    }

    abstract void connected(); // связь и родство предметов

    public void WellConnected(Object o){
        System.out.println("WellConnected");
    }


}
