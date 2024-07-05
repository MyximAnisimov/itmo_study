package common.utility;

import java.util.ArrayList;

public class UserIDNumber {
    public int globalUserId;
    public int countable;
    public static IDclass id = new IDclass(0);
    public int globalUserId2;
    public static int index = 0;
    public UserIDNumber(int list){
        this.globalUserId=list;
    }
    public void setGlobalUserId(int id){
        this.globalUserId=id;
    }
    public void setGlobalUserId2(int id){
        this.globalUserId2=id;
    }
    public int getGlobalID(){
        return globalUserId;
    }
    public int getGlobalUserId2(){
        return globalUserId2;
    }

    public void setCountable(int countable){
        this.countable = countable;
    }
    public int getCountable(){
        return countable;
    }
}
