public class World implements printable{
    private String name;
    private int id;
    private int time;
    public World(String name,int id,int time){
        this.name=name;
        this.id=id;
        this.time=time;
    }
    public World(){

    }
    @Override
    public String print(String name){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }
    public int hashCode(){
        return id;
    }
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        else if(obj==null||!(getClass()==obj.getClass()))
        { System.out.println( "eblan");
    }
        World world=(World) obj;

    return world.hashCode()==this.hashCode();

}

public void rename(String name){
        this.name=name;
       System.out.println(name);}
public int getTime(){
        return Time();
}
    private int Time(){
        return (time+45);
    }}
