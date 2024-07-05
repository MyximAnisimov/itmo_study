package web;

import java.util.Arrays;
import java.util.List;

public class CoordinatesValidator {
    private double x;
    private double y;
    private int r;
    public CoordinatesValidator(double x, double y, int r){
        this.x=x;
        this.y=y;
        this.r=r;
    }
    public boolean checkCoordinates(){
        return checkX() && checkY() && checkR();
    }
    public boolean checkX(){
        List<Integer> xValues = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3);
        return xValues.contains((int) x) && x == (int) x;
    }
    public boolean checkY(){
        return y > -5 && y < 5;
    }
    public boolean checkR(){
        List<Integer> rValues = Arrays.asList(1, 2, 3, 4, 5);
        return rValues.contains((int) r) && r == (int) r;
    }
}
