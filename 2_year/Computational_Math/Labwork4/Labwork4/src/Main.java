import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.lang.StrictMath.log;
import static java.lang.StrictMath.sin;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {
    public static String error_message = "";
    public static boolean has_discontinuity = false;

    private static double first_function(double x) {
        return 1 / x;
    }

    private static double second_function(double x) {
        return sin(x) / x;
    }

    private static double third_function(double x) {
        return x * x + 2;
    }

    private static double fourth_function(double x) {
        return 2 * x + 2;
    }

    private static double five_function(double x) {
        return log(x);
    }

    /*
     * How to use this function:
     *    Function<Double, Double> func = get_function(4);
     *    func.apply(0.0001);
     */
    private static Function<Double, Double> get_function(int n) {
        switch (n) {
            case (1):
                return Result::first_function;
            case (2):
                return Result::second_function;
            case (3):
                return Result::third_function;
            case (4):
                return Result::fourth_function;
            case (5):
                return Result::five_function;
            default:
                throw new UnsupportedOperationException("Function " + n + " not defined.");
        }
    }


    /*
     * Complete the 'calculate_integral' function below.
     *
     * The function is expected to return a DOUBLE.
     * The function accepts following parameters:
     *  1. DOUBLE a
     *  2. DOUBLE b
     *  3. INTEGER f
     *  4. DOUBLE epsilon
     */

    //    Fail
//    Test cases results (percentage):
//            case 0: 0.0%
//            case 1: 100.0%
//            case 2: 0.0%
//            case 3: 100.0%
//            case 4: 100.0%
//            case 5: 0.0%
//            case 6: 100.0%
    public static double calculate_integral(double a, double b, int f, double epsilon) {
        Result.has_discontinuity = false;

        Function<Double, Double> func = get_function(f);

//         if (a > b) {
//             double temp = a;
//             a = b;
//             b = temp;
//         }

        if (Double.isNaN(func.apply(a)) || Double.isNaN(func.apply(b))||  Double.isInfinite(func.apply(a)) || Double.isInfinite(func.apply(b))) {
            Result.has_discontinuity = true;
            Result.error_message = "Integrated function has discontinuity or does not defined in current interval";
            return 0.0;
        }

        double h = (b - a) / 2;

        double integral = (h / 3) * (func.apply(a) + 4 * func.apply(a + h) + func.apply(b));

        double prev_integral = integral;
        do {
            prev_integral = integral;
            h /= 2;
            double sum = 0;
            for (double x = a + h; x < b; x += 2 * h) {
                if (Double.isNaN(func.apply(x)) || Double.isInfinite(func.apply(x))) {
                    Result.has_discontinuity = true;
                    Result.error_message = "Integrated function has discontinuity or does not defined in current interval";
                    return 0.0;
                }
                sum += 4 * func.apply(x);
            }
            for (double x = a + 2 * h; x < b; x += 2 * h) {
                if (Double.isNaN(func.apply(x)) || Double.isInfinite(func.apply(x))) {
                    Result.has_discontinuity = true;
                    Result.error_message = "Integrated function has discontinuity or does not defined in current interval";
                    return 0.0;
                }
                sum += 2 * func.apply(x);
            }
            integral = (h / 3) * (func.apply(a) + func.apply(b) + sum);
        } while (((double) 1 /15) *Math.abs(integral - prev_integral) > epsilon);
        if(integral <= 0 && a >b){
            return integral;
        }
        else if((integral > 0 || integral < 0) && a <b){
            return integral;
        }
        else if(integral > 0 && a > b){
            return -integral;
        }
        else return integral;
    }

}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        double a = Double.parseDouble(bufferedReader.readLine().trim());

        double b = Double.parseDouble(bufferedReader.readLine().trim());

        int f = Integer.parseInt(bufferedReader.readLine().trim());

        double epsilon = Double.parseDouble(bufferedReader.readLine().trim());

        double result = Result.calculate_integral(a, b, f, epsilon);
        if(!Result.has_discontinuity){
            bufferedWriter.write(String.valueOf(result));
        } else {
            bufferedWriter.write(String.valueOf(Result.error_message));
        }
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}