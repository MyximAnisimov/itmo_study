package story;

import characters.Crowd;
import characters.Donut;
import characters.Dunno;
import characters.Human;
import environment.*;

public class storyTelling {
    private final Donut donut;
    private final Dunno dunno;
    private final Rooms room;
    private final Crowd cr1;
    private final Crowd cr2;
    private final Crowd cr3;
    private final Human.humanArray ha;
    public storyTelling(){
        this.donut= new Donut("Пончик",17,11);
        this.dunno=new Dunno("Незнайка",17,12);
    this.room=new Rooms(true);
    this.cr1=new Crowd();
        this.cr2=new Crowd();
        this.cr3=new Crowd();
    this.ha=new Human.humanArray((cr1,cr2,cr3));
    }
    public void go(){
new Places(){
            @Override
                    public void setFloor(Human human){
                human.setFloors(Floors.FIRST_FLOOR);
            }
        }.setFloor(this.donut);
System.out.println(donut.getName()+" находится на "+Floors.FIRST_FLOOR.getFloors());
donut.Thoughts(donut.getName()+" решил вернуться к "+dunno.getName());
room.Searchable(donut,true,80,dunno,ha);
};
}
