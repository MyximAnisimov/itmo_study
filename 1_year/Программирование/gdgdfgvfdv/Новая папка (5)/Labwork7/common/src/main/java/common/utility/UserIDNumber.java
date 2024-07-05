package common.utility;

import java.util.ArrayList;

public class UserIDNumber {
    public int globalUserId;
    public static int index = 0;
    public UserIDNumber(int list){
        this.globalUserId=list;
    }
    public void setGlobalUserId(int id){
        this.globalUserId=id;
    }
    public int getGlobalID(int id){
        return globalUserId;
    }
}
