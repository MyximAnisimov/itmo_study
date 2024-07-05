package common.utility;

public class VisibilityArgument {
public static boolean globalArgument=false;
public static boolean getGlobalArgumentTrue(){
    return globalArgument;
}
public static void setGlobalArgumentTrue(){
    globalArgument = true;
}
public static void setGlobalArgumentFalse(){
    globalArgument = false;
}
}
