package Exceptions;

public class RocketIsNotInSpace extends RuntimeException{
    public RocketIsNotInSpace(String number){
        super(number);
    }
}
