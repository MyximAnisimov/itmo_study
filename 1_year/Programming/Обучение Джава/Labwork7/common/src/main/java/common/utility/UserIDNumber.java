package common.utility;

import java.util.ArrayList;

public class UserIDNumber {
    public int globalUserId;
    public int globalUserId2;
    public static int index = 0;
    public UserIDNumber(int list, int list2){
        this.globalUserId=list;
        this.globalUserId2=list2;
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
}
