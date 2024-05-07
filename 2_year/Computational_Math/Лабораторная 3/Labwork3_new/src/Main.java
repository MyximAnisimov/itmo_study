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
import static java.lang.StrictMath.sin;
import static java.lang.StrictMath.pow;
import static java.lang.StrictMath.tan;

class SNAEFunctions {
    static double k = 0.4;
    static double a = 0.9;

    private static double first_function(List<Double> args) {
        return sin(args.get(0));
    }

    private static double second_function(List<Double> args) {
        return (args.get(0) * args.get(1)) / 2;
    }

    private static double third_function(List<Double> args) {
        return tan(args.get(0) * args.get(1) + k) - pow(args.get(0), 2);
    }

    private static double fourth_function(List<Double> args) {
        return a * pow(args.get(0), 2) + 2 * pow(args.get(1), 2) - 1;
    }

    private static double fifth_function(List<Double> args) {
        return pow(args.get(0), 2) + pow(args.get(1), 2) + pow(args.get(2), 2) - 1;
    }

    private static double six_function(List<Double> args) {
        return 2 * pow(args.get(0), 2) + pow(args.get(1), 2) - 4 * args.get(2);
    }


    private static double seven_function(List<Double> args) {
        return 3 * pow(args.get(0), 2) - 4 * args.get(1) + pow(args.get(2), 2);
    }

    private static double default_function(List<Double> args) {
        return 0.0;
    }

    /*
     * How to use this function:
     *    List<Function<Double, List<Double>> funcs = SNAEFunctions.get_functions(4);
     *    funcs[0].apply(List.of(0.0001, 0.002));
     */
    public static List<Function<List<Double>, Double>> get_functions(int n) {
        switch (n) {
            case (1):
                return List.of(SNAEFunctions::first_function, SNAEFunctions::second_function);
            case (2):{
                SNAEFunctions.k = 0.4;
                SNAEFunctions.a = 0.9;
                return List.of(SNAEFunctions::third_function, SNAEFunctions::fourth_function);
            }
            case (3):{
                SNAEFunctions.k = 0.0;
                SNAEFunctions.a = 0.5;
                return List.of(SNAEFunctions::third_function, SNAEFunctions::fourth_function);
            }
            case (4):
                return List.of(SNAEFunctions::fifth_function, SNAEFunctions::six_function, SNAEFunctions::seven_function);
            default:
                return List.of(SNAEFunctions::default_function);
        }
    }
}
class Result {
    private static final double EPSILON = 1e-5;

    public static List<Double> solve_by_fixed_point_iterations(int system_id, int number_of_unknowns, List<Double> initial_approximations) {
        List<Function<List<Double>, Double>> functions = SNAEFunctions.get_functions(system_id);

        List<List<Double>> jacobianMatrix;
        List<Double> fValues;
        List<Double> deltaX = new ArrayList<>(Collections.nCopies(number_of_unknowns, 1.0)); // Initialize deltaX with dummy values

        while (deltaX.stream().mapToDouble(Double::doubleValue).anyMatch(val -> Math.abs(val) > EPSILON)) {
            jacobianMatrix = calculateJacobianMatrix(functions, initial_approximations);
            fValues = calculateFunctionValues(functions, initial_approximations);
            deltaX = solveLinearSystem(jacobianMatrix, fValues);

            for (int i = 0; i < number_of_unknowns; i++) {
                initial_approximations.set(i, initial_approximations.get(i) - deltaX.get(i));
            }
        }

        return initial_approximations;
    }

    private static List<List<Double>> calculateJacobianMatrix(List<Function<List<Double>, Double>> functions, List<Double> args) {
        int n = args.size();
        List<List<Double>> jacobianMatrix = new ArrayList<>();

        for (int i = 0; i < n; i++) {List<Double> rowValues = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                double delta = 1e-6;

                List<Double> newArgsPlus = new ArrayList<>(args);
                newArgsPlus.set(j, args.get(j) + delta);
                double fxPlus = functions.get(i).apply(newArgsPlus);

                List<Double> newArgsMinus = new ArrayList<>(args);
                newArgsMinus.set(j, args.get(j) - delta);
                double fxMinus = functions.get(i).apply(newArgsMinus);

                double partialDerivative = (fxPlus - fxMinus) / (2 * delta);
                rowValues.add(partialDerivative);
            }
            jacobianMatrix.add(rowValues);
        }

        return jacobianMatrix;
    }

    private static List<Double> calculateFunctionValues(List<Function<List<Double>, Double>> functions, List<Double> args) {
        List<Double> fValues = new ArrayList<>();
        for (Function<List<Double>, Double> function : functions) {
            fValues.add(function.apply(args));
        }
        return fValues;
    }

    private static List<Double> solveLinearSystem(List<List<Double>> matrixA, List<Double> vectorB) {
        int n = vectorB.size();
        List<List<Double>> augMatrix = new ArrayList<>(n);

        for (int i = 0; i < n; i++) {
            List<Double> row = new ArrayList<>(matrixA.get(i));
            row.add(vectorB.get(i));
            augMatrix.add(row);
        }

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                double factor = augMatrix.get(j).get(i) / augMatrix.get(i).get(i);
                for (int k = i; k < n + 1; k++) {
                    double newValue = augMatrix.get(j).get(k) - (factor * augMatrix.get(i).get(k));
                    augMatrix.get(j).set(k, newValue);
                }
            }
        }

        List<Double> solution = new ArrayList<>(Collections.nCopies(n, 0.0));
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += augMatrix.get(i).get(j) * solution.get(j);
            }
            solution.set(i, (augMatrix.get(i).get(n) - sum) / augMatrix.get(i).get(i));
        }

        return solution;
    }
}

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int system_id = Integer.parseInt(bufferedReader.readLine().trim());

        int number_of_unknowns = Integer.parseInt(bufferedReader.readLine().trim());

        List<Double> initial_approximations = IntStream.range(0, number_of_unknowns).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Double::parseDouble)
                .collect(toList());

        List<Double> result = Result.solve_by_fixed_point_iterations(system_id, number_of_unknowns, initial_approximations);

        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n"
        );
        bufferedReader.close();
        bufferedWriter.close();
    }
}