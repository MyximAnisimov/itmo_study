public class Rocket {
    private String name;
    public class Remote{
        private String setDirect;
        public Remote(String setDirect){
            this.setDirect=setDirect;
        }
        public void changeDirect(){
            name=setDirect;
        }
        public String getName(){
            return name;}
        public void Inf() {
            System.out.println("Направление изменено: " + this.getName());
        }
    }
    public static class Engine{
        public boolean IsEngineOn;
        public int OilLevel;
        public Engine(boolean IsEngineOn, int OilLevel){
            this.IsEngineOn=IsEngineOn;
            this.OilLevel=OilLevel;
        }
        public void Losses(){
            if(IsEngineOn==true){
                OilLevel=OilLevel/2;
                System.out.println("Топлива осталось: "+OilLevel);
            }
            else{
                System.out.println("Двигатель не включён, топливо не расходуется");
            }
        }
    }
    public Rocket(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public void SentInf(){
        System.out.println("Name of the Rocket: "+this.getName());
    }
public void devices(){
        class Termometr{
            private int temperature;
            public Termometr(int temp){
                this.temperature=temp;
            }
            public int Hightemp(){
                return temperature+10;
            }
        }
        Termometr t1= new Termometr(10);
        t1.Hightemp();
        class Barometer{
            private int pressure;
            public Barometer(int pressure){
                this.pressure=pressure;
            }
            public int Highrbar(){
                return pressure+20;
            }
        }
        Barometer B1= new Barometer(100);
        System.out.println(B1.Highrbar());
        System.out.println(t1.Hightemp());

}


}
