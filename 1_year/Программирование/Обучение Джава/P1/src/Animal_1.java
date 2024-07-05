public abstract class Animal_1 {
    private String name;
    private int age;
    private String livingArea;
    Animal_1(String name,int age,String livingArea){
        this.name=name;
        this.age=age;
        this.livingArea=livingArea;
    }

    public void setName(String name) {

        this.name = name;
    }
    public void setAge(int age){
        this.age=age;
    }

    public void setLivingArea(String livingArea) {
        this.livingArea = livingArea;
    }

    public String getName() {
        return name;
    }
    public int getAge(){
        return age;
    }
    public String getlivingArea(){
        return livingArea;
    }
}

