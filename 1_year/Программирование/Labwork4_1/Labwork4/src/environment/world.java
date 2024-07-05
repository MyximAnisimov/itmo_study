package environment;

import characters.human;

public class world implements sensable {
    private String time;
    private boolean EngineSound;
    private boolean Gravity;
    public world(String time){
        this.time = time;
        if (time.equals(environment.time.LATE_TIME.getTime())) {
            System.out.println("Время позднее, Пончик быстро заснул, история закончилась :(");
            System.exit(0);
        }
    }
    @Override
    public void Sensable(human human, boolean EngineSound, boolean Gravity) {
        this.EngineSound = EngineSound;
        this.Gravity = Gravity;
        if (EngineSound && !Gravity) {
            System.out.println(human.getName() + " догадался, что ракета находится в космосе");
        } else if (!EngineSound || Gravity) {
            System.out.println((human.getName() + " подумал, что ракета на земле"));
        }
    }
}



