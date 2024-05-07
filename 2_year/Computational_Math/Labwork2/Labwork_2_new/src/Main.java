
import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.lang.Math.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
class Result {

    public static boolean isSolutionExists = true;
    public static String errorMessage = "The system has no roots of equations or has an infinite set of them.";

    public static boolean isInputMatrixSymmetrical(List<List<Double>> inputMatrix) {

        if (inputMatrix.size() < inputMatrix.get(0).size() - 1) {
            isSolutionExists = false;
            return isSolutionExists;
        }
        return isSolutionExists;
    }
    public static Double[] getVectorB(List<List<Double>> inputMatrix) {
        Double[] vectorB = new Double[inputMatrix.size()];

        for (int i = 0; i < inputMatrix.size(); i++) {
            for (int j = 0; j < inputMatrix.get(i).size(); j++) {
                if (j == inputMatrix.get(i).size() - 1) vectorB[i] = inputMatrix.get(i).get(j);
            }
        }
        return vectorB;
    }
    public static void fillMatrixes(int n, List<List<Double>> inputMatrix, Double[][] matrixB, Double[][] matrixC){
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){

                if(j == 0) {
                    matrixB[i][j] = inputMatrix.get(i).get(j);
                }
                else if(i >= j && j > 0){
                    double sum = 0.0;
                    for(int k=0; k< j; k++){
                        sum = sum + matrixB[i][k] * matrixC[k][j];
                    }
                    matrixB[i][j] = inputMatrix.get(i).get(j) - sum;
                }
                else {
                    matrixB[i][j]= 0.0;
                }

                if(0 < i && i < j){
                    double sum = 0.0;
                    for(int k=0; k<i; k++){
                        sum = sum + matrixB[i][k] * matrixC[k][j];
                    }
                    matrixC[i][j] = (inputMatrix.get(i).get(j)- sum) / matrixB[i][i];
                }
                else if(i == 0){
                    matrixC[i][j] = inputMatrix.get(i).get(j) / matrixB[i][i];
                }
                else if(i==j){
                    matrixC[i][j] = 1.0;
                }
                else {
                    matrixC[i][j] = 0.0;
                }
            }
        }
    }

    public static void makeInversionMatrix(Double[][] matrix, int matrixSize) {
        double temp;

        Double[][] identityMatrix = new Double[matrixSize][matrixSize];


        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                identityMatrix[i][j] = 0.0;

                if (i == j) identityMatrix[i][j] = 1.0;
            }
        }

        for (int k = 0; k < matrixSize; k++) {
            temp = matrix[k][k];

            for (int j = 0; j < matrixSize; j++) {
                if (temp == 0.0) {
                    isSolutionExists = false;
                    return;
                }
                else {
                    matrix[k][j] /= temp;
                    identityMatrix[k][j] /= temp;
                }
            }

            for (int i = k + 1; i < matrixSize; i++) {
                temp = matrix[i][k];

                for (int j = 0; j < matrixSize; j++) {
                    matrix[i][j] -= matrix[k][j] * temp;
                    identityMatrix[i][j] -= identityMatrix[k][j] * temp;
                }
            }
        }

        for (int k = matrixSize - 1; k > 0; k--) {
            for (int i = k - 1; i >= 0; i--) {
                temp = matrix[i][k];

                for (int j = 0; j < matrixSize; j++) {
                    matrix[i][j] -= matrix[k][j] * temp;
                    identityMatrix[i][j] -= identityMatrix[k][j] * temp;
                }
            }
        }

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) matrix[i][j] = identityMatrix[i][j];
        }

    }
    public static Double[] getCalculatedVector(Double[][] matrix, Double[] vectorB) {

        Double[] vectorY = new Double[vectorB.length];
        Double elementValue = 0.0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                elementValue = elementValue + vectorB[j] * matrix[i][j];
            }

            vectorY[i] = elementValue;
            elementValue = 0.0;
        }

        return vectorY;
    }

    public static List<Double> solveByCholeskyDecomposition(int n, List<List<Double>> matrix) {
        if(!isInputMatrixSymmetrical(matrix)){
            return null;
        }
        Double[] vectorB = getVectorB(matrix);

        Double[][] matrixB = new Double[n][n];
        Double[][] matrixC = new Double[n][n];

        fillMatrixes(n, matrix, matrixB, matrixC);
        makeInversionMatrix(matrixB, n);
        makeInversionMatrix(matrixC, n);

        Double[] vectorY = getCalculatedVector(matrixB, vectorB);
        Double[] vectorX = getCalculatedVector(matrixC, vectorY);

        List<Double> result = new ArrayList<>();
        result.addAll(Arrays.stream(vectorY).toList());
        result.addAll(Arrays.stream(vectorX).toList());
    return result;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int matrixRows = n;
        int matrixColumns = n + 1;

        List<List<Double>> matrix = new ArrayList<>();

        IntStream.range(0, matrixRows).forEach(i -> {
            try {
                matrix.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Double::parseDouble)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Double> result = Result.solveByCholeskyDecomposition(n, matrix);

        if (Result.isSolutionExists) {
            bufferedWriter.write(
                    result.stream()
                            .map(Object::toString)
                            .collect(joining("\n"))
                            + "\n"
            );
        } else {
            bufferedWriter.write(Result.errorMessage + "\n");
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
}