package web;

import static java.lang.Math.abs;

public class AreaChecker {
    public boolean getMessage(String coordinateX, String coordinateY, String coordinateR){
            double double_coordinateX = Double.parseDouble(coordinateX);
            double double_coordinateY = Double.parseDouble(coordinateY);
            double int_coordinateR = Integer.parseInt(coordinateR);
            if(double_coordinateX<=0&&double_coordinateY>=0)
                return abs(double_coordinateX) < abs(int_coordinateR) && double_coordinateY < int_coordinateR / 2;
            else if(double_coordinateX>=0&&double_coordinateY>=0)
                return (double_coordinateX <= int_coordinateR) && (double_coordinateY <= int_coordinateR/2) && (double_coordinateX + 2*double_coordinateY <= int_coordinateR);
            else if(double_coordinateX<=0&&double_coordinateY<=0)
                return ((double_coordinateX * double_coordinateX + double_coordinateY * double_coordinateY) <= ((int_coordinateR/2) * (int_coordinateR/2)));
            else return false;
        }
    }

