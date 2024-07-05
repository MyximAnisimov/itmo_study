package common.utility.requests;

public class LoginCommandRequest extends Request{
    public String user;
    public String password;
    public LoginCommandRequest(String user, String password){
        super("login");
        this.password=password;
                this.user=user;
    }
}
