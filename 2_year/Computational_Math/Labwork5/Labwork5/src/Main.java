import java.io.*;
import java.util.function.*;
import static java.lang.StrictMath.sin;

class Result {

    private static double first_function(double x, double y) {
        return sin(x);
    }

    private static double second_function(double x, double y) {
        return (x * y)/2;
    }

    private static double third_function(double x, double y) {
        return y - (2 * x)/y;
    }

    private static double fourth_function(double x, double y) {
        return x + y;
    }

    private static double default_function(double x, double y) {
        return 0.0;
    }

    /*
     * How to use this function:
     *    BiFunction<Double, Double, Double> func = get_function(4);
     *    func.apply(0.0001);
     */
    private static BiFunction<Double, Double, Double> get_function(int n) {
        switch (n) {
            case (1):
                return Result::first_function;
            case (2):
                return Result::second_function;
            case (3):
                return Result::third_function;
            case (4):
                return Result::fourth_function;
            default:
                return Result::default_function;
        }
    }

    /*
     * Complete the 'solveByEulerImproved' function below.
     *
     * The function is expected to return a DOUBLE.
     * The function accepts following parameters:
     *  1. INTEGER f
     *  2. DOUBLE epsilon
     *  3. DOUBLE a
     *  4. DOUBLE y_a
     *  5. DOUBLE b
     */
    public static double solveByEulerImproved(int f, double epsilon, double a,  double y_a, double b) {
        double h = 0.1;
        double y = y_a;
        double x = a;

        BiFunction<Double, Double, Double> func = get_function(f);

        while (x < b) {
            double y_next = y + h * func.apply(x, y);

            double y_mid = y + h/2 * (func.apply(x, y) + func.apply(x + h, y_next));
            double y_next_improved = y + h * func.apply(x + h/2, y_mid);

            double error = Math.abs(y_next_improved - y_next);
//            double tau = 0.9;
            h = h * Math.sqrt(epsilon / (2 * error));

            y = y_next_improved;
            x += h;
        }

        return y;
    }

}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int f = Integer.parseInt(bufferedReader.readLine().trim());

        double epsilon = Double.parseDouble(bufferedReader.readLine().trim());

        double a = Double.parseDouble(bufferedReader.readLine().trim());

        double y_a = Double.parseDouble(bufferedReader.readLine().trim());

        double b = Double.parseDouble(bufferedReader.readLine().trim());

        double result = Result.solveByEulerImproved(f, epsilon, a, y_a, b);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}