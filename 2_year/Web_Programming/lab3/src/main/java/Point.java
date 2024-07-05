import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Point implements Serializable {

    private double x;
    private double y;
    private float r = 1;
    private String res;
//    LocalDateTime dateTime = LocalDateTime.now();
    private String owner;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }
//    public String getDate(LocalDateTime dateTime) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//        String str = dateTime.format(formatter);
//        return str;
//    }

    @Override
    public String toString() {
        return "<tr>" +
                "<td>" + this.x + "</td>" +
                "<td>" + this.y + "</td>" +
                "<td>" + this.r + "</td>" +
                "<td>" + this.res + "</td>" +
                "</tr>";
    }

    public void check() {
        if ((x * x + y * y <= (r * r)/4 && x >= 0 && y <= 0) ||
                (y <= r && x <= r/2 && y >= 0) ||
                ((x <= -r) && (y <= -r/2) && (x + 2*x <= -r) &&  x <= 0 && y <= 0)) {
            res = "True";
        } else {
            res = "False";
        }
    }
}
