package Items;

public class MoonRock extends Item{


    public MoonRock(float length, float height, float width, int energy,int id) {
        super(length, height, width, energy,id);
    }

    @Override
    void connected() {
        System.out.println("Совместим с Магнитом");
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() == o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return item.getId() == this.getId();
    }
    @Override
    public int hashCode() {
        return 31 * id;
    }



}
