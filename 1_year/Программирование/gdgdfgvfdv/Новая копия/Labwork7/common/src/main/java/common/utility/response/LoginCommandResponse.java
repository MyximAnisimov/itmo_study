package common.utility.response;

public class LoginCommandResponse extends Response{
    public String infoMessage;
    public boolean SuccLogin;
    public String token;

    public LoginCommandResponse(String infoMessage, String error) {
        super("login", error);
        this.infoMessage = infoMessage;
    }

}
