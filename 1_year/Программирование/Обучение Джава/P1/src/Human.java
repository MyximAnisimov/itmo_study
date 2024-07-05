abstract class Human {
    private int heigth;
    private int weigth;
    private String username;
    Human(String name){
        this.username=username;
    }
    Human(int heigth,int weigth,String username){
        this.heigth=heigth;
        this.weigth=weigth;
        this.username=username;
    }
    public void setHeigth(int heigth){
        this.heigth=heigth;
    }
    public void setWeigth(int weigth){
        this.weigth=weigth;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public int getHeigth(){
        return heigth;
    }
    public int getWeigth(){
        return weigth;
    }
    public String getUsername(){
        return username;
    }
}
