package server.utility;

public class ResponseOutputer {
private static final StringBuilder stringBuilder = new StringBuilder();
public static void append(Object out){
    stringBuilder.append(out);
}
public static void appendLn(){
    stringBuilder.append("\n");
}
public static void appendLn(Object out){
    stringBuilder.append("error: ").append(out).append("\n");
}
public static void appendError(Object out){
    stringBuilder.append("error: ").append(out).append("\n");
}
public static String clearBuffer(){
    String toReturn = stringBuilder.toString();
    stringBuilder.delete(0, stringBuilder.length());
    return toReturn;
}
}
