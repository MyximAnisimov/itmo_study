package Items;

import Actor.Human;
import Actor.State;

public class Remote extends Item implements Weapon{
    boolean OnOFF = false; // true - включить гравитацию , false - отключить гравитацию ;

    private int  MoonRock_Condition = 0 ;
    private int  Magnet_Condition = 0;
    public Remote(float length, float height, float width, Magnet magnet, MoonRock moonRock) {
        super(length, height, width, magnet, moonRock);
        this.Magnet_Condition = magnet.energy;
        this.MoonRock_Condition = moonRock.energy;
    }

    public void changeGravity(Human user,Human target,boolean onOFF){

        System.out.println(user.getName() + " решается воспользоватсья пультом и направляет его против -> " + target.getName() );

        this.OnOFF = onOFF;
        if ( onOFF && (MoonRock_Condition + Magnet_Condition > 0 ) && user.getName().equals("Знайка")){
            target.setLevitation(State.FLying.getState());
        } else if (MoonRock_Condition + Magnet_Condition == 0) {
            System.out.println("Пульт не работает , вставьте в пульт лунный камень и магнит или зарядите их поле energy до 100");
        } else if ( (MoonRock_Condition + Magnet_Condition < 0 ) ){
            System.out.println("Пульт не работает , лунный камень и магнит отрицательно заряжены");
        } else if ( (!onOFF) && (MoonRock_Condition + Magnet_Condition > 0 ) && user.getName().equals("Знайка")){
            target.setLevitation(State.OnGround.getState());
        } else if (!user.getName().equals("Знайка")){
            System.out.println("Доступ к пульту запрещен!!!!!!!!!");
        }
        else {
            System.out.println("Пульт не работает , вы все сломали");
        }
    }



    @Override
    void connected() {
        System.out.println("Совместим с Магинтом и Лунным Камнем ");
    }
}
