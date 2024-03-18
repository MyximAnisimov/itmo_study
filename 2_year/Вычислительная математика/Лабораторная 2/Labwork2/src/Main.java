
import java.io.*;
import java.util.*;
import java.util.stream.*;

import static java.lang.Math.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    public static boolean isSolutionExists = true;
    public static String errorMessage = "Error message";

    public static boolean isInputMatrixSymmetrical(List<List<Double>> inputMatrix) {

        if (inputMatrix.size() < inputMatrix.get(0).size() - 1) {
            isSolutionExists = false;
            return isSolutionExists;
        }

        for (int i = 0; i < inputMatrix.size(); i++) {
            for (int j = 0; j < inputMatrix.get(i).size() - 1; j++) {
                if (!Objects.equals(inputMatrix.get(i).get(j), inputMatrix.get(j).get(i))) {
                    isSolutionExists = false;
                    return isSolutionExists;
                }
            }
        }
        return isSolutionExists;
    }

    public static void isMatrixPositiveDefinite(Double[][] matrixL, List<List<Double>> inputMatrix) {
        Double elementValue = 0.0;
        Double[][] notTransponatedMatrixL = new Double[matrixL.length][matrixL[0].length];
        Double[][] transponatedMatrixL = new Double[matrixL[0].length][matrixL.length];

        for (int i = 0; i < matrixL.length; i++) {
            notTransponatedMatrixL[i] = Arrays.copyOf(matrixL[i], matrixL[i].length);
            transponatedMatrixL[i] = Arrays.copyOf(matrixL[i], matrixL[i].length);
        }

        transponateMatrix(transponatedMatrixL);

        for (int i = 0; i < matrixL.length; i++) {
            for (int j = 0; j < matrixL.length; j++) {
                for (int k = 0; k < matrixL.length; k++) {
                    elementValue = elementValue + notTransponatedMatrixL[j][k] * transponatedMatrixL[k][i];
                }

                if (!Objects.equals(inputMatrix.get(i).get(j), elementValue) && abs(inputMatrix.get(i).get(j) - elementValue) > 0.1) {
                    isSolutionExists = false;
                    return;
                }
                elementValue = 0.0;
            }
        }
    }

    public static Double[][] makeStepMatrix(int n, List<List<Double>> inputMatrix) {

        Double[][] stepMatrix = new Double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j > i) stepMatrix[i][j] = 0.0;
                else stepMatrix[i][j] = inputMatrix.get(i).get(j);
            }
        }
        return stepMatrix;
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

    public static Double[][] getMatrixL(Double[][] stepMatrix) {

        Double[][] matrixL = new Double[stepMatrix.length][stepMatrix.length];
        Double bufferElement = 0.0;
        Double calculationElement = 0.0;

        for (int i = 0; i < stepMatrix.length; i++) {
            for (int j = 0; j < stepMatrix.length; j++) {
                if (i == j) {
                    for (int k = 0; k < matrixL.length; k++) {
                        if (matrixL[j][k] != null) calculationElement = calculationElement + pow(matrixL[j][k], 2);
                    }

                    if (stepMatrix[j][j] - calculationElement < 0) {
                        isSolutionExists = false;
                        break;
                    }
                    else matrixL[i][j] = sqrt(stepMatrix[j][i] - calculationElement);

                    calculationElement = 0.0;

                }
                else if (i > j) {
                    for (int l = 0; l < matrixL.length; l++) {
                        if (matrixL[i][l] != null && matrixL[j][l] != null && l != i && l != j) {
                            bufferElement = matrixL[i][l] * matrixL[j][l];
                        }
                        else bufferElement = 0.0;

                        calculationElement = calculationElement + bufferElement;
                    }

                    if (matrixL[j][j] == null || matrixL[j][j] == 0.0) {
                        isSolutionExists = false;
                        break;
                    }
                    else matrixL[i][j] = (stepMatrix[i][j] - calculationElement) / matrixL[j][j];

                    calculationElement = 0.0;

                }
                else matrixL[i][j] = 0.0;
            }
        }
        return matrixL;
    }

    public static void transponateMatrix(Double[][] matrixL) {

        for (int i = 0; i < matrixL.length; i++) {
            for (int j = i + 1; j < matrixL.length; j++) {
                Double temp = matrixL[i][j];
                matrixL[i][j] = matrixL[j][i];
                matrixL[j][i] = temp;
            }
        }
    }

    public static void makeInversionMatrix(Double[][] matrixL, int matrixSize) {
        double temp;

        Double[][] E = new Double[matrixSize][matrixSize];


        for (int i = 0; i < matrixSize; i++)
            for (int j = 0; j < matrixSize; j++) {
                E[i][j] = 0.0;

                if (i == j) E[i][j] = 1.0;
            }

        for (int k = 0; k < matrixSize; k++) {
            temp = matrixL[k][k];

            for (int j = 0; j < matrixSize; j++) {
                if (temp == 0.0) {
                    isSolutionExists = false;
                    return;
                }
                else {
                    matrixL[k][j] /= temp;
                    E[k][j] /= temp;
                }
            }

            for (int i = k + 1; i < matrixSize; i++) {
                temp = matrixL[i][k];

                for (int j = 0; j < matrixSize; j++) {
                    matrixL[i][j] -= matrixL[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }

        for (int k = matrixSize - 1; k > 0; k--) {
            for (int i = k - 1; i >= 0; i--) {
                temp = matrixL[i][k];

                for (int j = 0; j < matrixSize; j++) {
                    matrixL[i][j] -= matrixL[k][j] * temp;
                    E[i][j] -= E[k][j] * temp;
                }
            }
        }

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) matrixL[i][j] = E[i][j];
        }

    }

    public static Double[] getCalculatedVector(Double[][] matrixL, Double[] vectorB) {

        Double[] vectorY = new Double[vectorB.length];
        Double elementValue = 0.0;

        for (int i = 0; i < matrixL.length; i++) {
            for (int j = 0; j < matrixL.length; j++) {
                elementValue = elementValue + vectorB[j] * matrixL[i][j];
            }

            vectorY[i] = elementValue;
            elementValue = 0.0;
        }

        return vectorY;
    }

    public static List<Double> solveByCholeskyDecomposition(int n, List<List<Double>> matrix) {

        if (!isInputMatrixSymmetrical(matrix)) return null;

        Double[][] stepMatrix = makeStepMatrix(n, matrix);
        Double[] vectorB = getVectorB(matrix);
        Double[][] matrixL = getMatrixL(stepMatrix);

        if (!isSolutionExists) return null;
        isMatrixPositiveDefinite(matrixL, matrix);

        Double[][] transponatedMatrixL = getMatrixL(stepMatrix);
        if (!isSolutionExists) return null;

        transponateMatrix(transponatedMatrixL);
        makeInversionMatrix(transponatedMatrixL, n);
        makeInversionMatrix(matrixL, n);

        Double[] vectorY = getCalculatedVector(matrixL, vectorB);
        Double[] vectorX = getCalculatedVector(transponatedMatrixL, vectorY);

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