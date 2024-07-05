package common.utility.requests;

public class LoginCommandRequest extends Request{
    public String user;
    public String password;
    public int user_id;
    public int count;
    public LoginCommandRequest(String user, String password, int user_id, int count){
        super("login");
        this.password=password;
                this.user=user;
                this.user_id=user_id;
                this.count = count;
    }
    public void setUser(int user_id){
        this.user_id= user_id;
    }
    public void setCount(int count){
        this.count=count;
    }
    public String getUser(){
        return user;
    }
}
