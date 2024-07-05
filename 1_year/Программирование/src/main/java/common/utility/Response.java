package common.utility;

import java.io.Serializable;

public class Response implements Serializable {
    private ResponseResult responseResult;
    private String responseBody;
    public Response(ResponseResult responseResult, String responseBody)
    {
        this.responseResult = responseResult;
        this.responseBody = responseBody;
    }
    public String getResponseBody(){
    return responseBody;}
    public ResponseResult responseResult(){
        return responseResult;
    }
}
