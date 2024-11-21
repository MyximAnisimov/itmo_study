package Items;

public class Magnet extends Item{


    public Magnet(float length, float height, float width, int energy,int id) {
        super(length, height, width, energy,id);
    }
    @Override
    void connected() {
        System.out.println("Совместим с Лунным Камнем") ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() == o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return (item.getId() == this.getId() && item.hashCode() == this.hashCode());
    }
    @Override
    public int hashCode() {
        return 31 * id;
    }
}
