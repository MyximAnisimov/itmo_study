package Items;

import java.util.SortedMap;

public class Case extends Item{

    private String WaterProof = State.WATERPROOF.getState();
    private String FireProof = State.FIREPROOF.getState();



    public Case(float length, float height, float width) {
        super(length, height, width);
    }

    @Override
    void connected() {
        System.out.println("Должен быть соединен с Пультом");
    }

    @Override
    public void WellConnected(Object o){
        Remote remote = (Remote) o;
        System.out.println("Пульт находится в огнеупорном и водостойком кейсе");
    }

}

