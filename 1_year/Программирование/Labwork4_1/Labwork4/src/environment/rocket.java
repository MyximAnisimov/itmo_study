package environment;

import characters.donut;

public abstract class rocket {
    private String name;
    private int SerialNumber;
    public rocket(String name, int SerialNumber){
        this.name=name;
        this.SerialNumber=SerialNumber;
    }
    public rocket(){}
public static class Engine{
    private boolean IsOn;
    private int FuelLevel;
    public Engine(boolean IsOn, int FuelLevel){
        this.IsOn=IsOn;
        this.FuelLevel=FuelLevel;

    }
    public void check() throws Exception{
        class negFuelNumber extends Exception{
            public negFuelNumber(String message){
                super(message);
            }
        }
        if(FuelLevel<0){
            throw new negFuelNumber("Уровень топлива не может быть ниже 0!");
        }
        else if(FuelLevel==0){
            setisOn(false);
            System.out.println("Уровень топлива: "+FuelLevel);
            System.out.println("Режим работы двигателя (true - работает, false -  не работает): "+IsOn);
        }
        else {
            System.out.println("Уровень топлива: "+FuelLevel);
            System.out.println("Режим работы двигателя (true - работает, false -  не работает): "+IsOn);
        }
    }
    public void setisOn(boolean isOn){
        this.IsOn=IsOn;
    }
    public boolean getisOn(){
        return IsOn;
    }


}
    public void OnFloor(donut donut){
    }

    public int getSerialNumber(){
        return SerialNumber;
    }
    @Override
    public int hashCode(){
    return getSerialNumber();
    }
    @Override
    public boolean equals(Object o){
        if (this==o){
            return true;
        }
        else if(this==null){
            return false;
        }
        else if(getClass()==o.getClass()){
            return true;
        }
        rocket Rocket=(rocket) o;
        return this.hashCode()==Rocket.hashCode();
    }
    @Override
    public String toString(){
        return "Rocket{" +
                "name= "+name+
                ", serial number= "+getSerialNumber()+'}';}
    }

