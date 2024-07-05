package server.dao;

import common.collections.Location;
import jakarta.validation.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
@Entity(name="locations")
@Table(name="locations", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class LocationDAO implements Serializable {
public LocationDAO(){}
    public LocationDAO(Location location){
        this.x=location.getX();
        this.y=location.getY();
        this.z=location.getZ();
        this.name=location.getName();
    }
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false, unique=true,length=11)
    private int id;

    @NotBlank(message = "Координата х класса Location не может быть пустым")
    @Column(name="x", nullable=false)
    private Long x;

    @Column(name="y", nullable=false)
    private int y;

    @NotBlank(message = "Координата z класса Location не может быть пустым")
    @Column(name="z", nullable=false)
    private Float z;

    @NotBlank(message = "Поле name класса Location не может быть пустым")
    @Column(name="name", nullable=false)
    private String name;

    @ManyToOne
    @JoinColumn(name="creator_id", nullable=false)
    private LocationDAO creator;
    public LocationDAO getCreator() {
        return creator;
    }

    public void setCreator(LocationDAO creator) {
        this.creator = creator;
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
}
