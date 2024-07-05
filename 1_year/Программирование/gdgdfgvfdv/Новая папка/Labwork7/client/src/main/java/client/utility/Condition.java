package client.utility;

import java.beans.Visibility;

public class Condition {
    String token = "unlogged_user";
    Visibility v;
    public void setV(Visibility v){
        this.v=v;
    }
    public Visibility getV(){
        return v;
    }
}
