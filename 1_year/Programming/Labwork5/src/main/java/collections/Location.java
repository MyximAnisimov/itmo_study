package collections;

import tools.Validatable;

import java.util.Objects;

public class Location implements Validatable {
    private Long x;
    private int y;
    private Float z;
    private String name;
    public Location(Long x, int y, Float z, String name){
        this.x=x;
        this.y=y;
        this.z=z;
        this.name=name;
    }
    public Long getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public Float getZ(){
        return z;
    }
    public String getName(){
        return name;
    }
    @Override
    public boolean validate(){
        if(x==null){
            return false;
        }
        if(z==null){
            return false;
        }
        return name==null||this.getName().isEmpty();
    }
    @Override
    public boolean equals(Object o){
        if(this==o){
            return true;
        }
        else if(o==null||o.getClass()!=getClass()){
            return false;
        }
        Location loc=(Location) o;
        return Objects.equals(x,loc.x)&& Objects.equals(y,loc.y)&&Objects.equals(z,loc.z)&&Objects.equals(name,loc.name);
    }
    @Override
    public int hashCode(){
        return Objects.hash(x,y,z,name);
    }
    @Override
    public String toString(){
        return "Coordinates: { X= "+x+", "+
                "Y= "+y+", "+
    "Z= "+z+", "+"Name= "+name;}
    }

