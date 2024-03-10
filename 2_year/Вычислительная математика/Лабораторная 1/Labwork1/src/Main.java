import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toList;


class Result {
    public static double interpolate_by_lagrange(List<Double> x_axis, List<Double> y_axis, double x) {
        double total_result = 0.0;
        double nominator = 1.0;
        double denominator = 1.0;
        double current_argument = 0.0;
        int x_axis_size = x_axis.size();
        for(int i=0; i<x_axis_size; i++){
            current_argument = x_axis.get(i);
           for(int j=0; j<x_axis_size; j++){
               if(i != j) {
                   nominator = nominator * (x - x_axis.get(j));
                   denominator = denominator * (current_argument - x_axis.get(j));
                if(denominator ==0) {
                    nominator = 0;
                    denominator = 1;
                    break;
                }
               }
           }
            total_result = total_result + (nominator / denominator) * y_axis.get(i);
            nominator = 1.0;
            denominator = 1.0;

        }
        return total_result;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int axisCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Double> x_axis = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Double::parseDouble)
                .collect(toList());

        List<Double> y_axis = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Double::parseDouble)
                .collect(toList());

        double x = Double.parseDouble(bufferedReader.readLine().trim());

        double result = Result.interpolate_by_lagrange(x_axis, y_axis, x);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}