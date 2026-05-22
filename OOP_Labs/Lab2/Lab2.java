import java.util.Random;

public class Lab2 {
    public static void main(String[] args) {
        int rows = 3, cols = 4;
        
        double[][] matrixA = createMatrix(rows, cols);
        double[][] matrixB = createMatrix(rows, cols);
        
        System.out.println("Matrix A:"); printMatrix(matrixA);
        System.out.println("Matrix B:"); printMatrix(matrixB);
        
        try {
            double[][] matrixC = addMatrices(matrixA, matrixB);
            System.out.println("Result Matrix C (A + B):"); printMatrix(matrixC);
            
            double finalSum = calculateExtremumsSum(matrixC);
            System.out.printf("\nSum of extremums (Min in even, Max in odd cols): %.4f\n", finalSum);
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Динамічне створення матриці double (C7=0) за допомогою Random
    public static double[][] createMatrix(int rows, int cols) {
        double[][] matrix = new double[rows][cols];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextDouble() * 50; 
            }
        }
        return matrix;
    }

    // Математичне додавання матриць (C5=2) з валідацією розмірностей
    public static double[][] addMatrices(double[][] A, double[][] B) {
        if (A.length != B.length || A[0].length != B[0].length) {
            throw new IllegalArgumentException("Matrix dimensions must match for addition!");
        }
        int rows = A.length;
        int cols = A[0].length;
        double[][] C = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    // Пошук екстремумів по стовпцях (C11=7): Min у парних, Max у непарних
    public static double calculateExtremumsSum(double[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        double sum = 0;

        for (int j = 0; j < cols; j++) {
            double extremum = matrix[0][j];
            for (int i = 1; i < rows; i++) {
                if (j % 2 == 0) { // Парний стовпець
                    if (matrix[i][j] < extremum) extremum = matrix[i][j];
                } else { // Непарний стовпець
                    if (matrix[i][j] > extremum) extremum = matrix[i][j];
                }
            }
            sum += extremum;
        }
        return sum;
    }

    public static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double val : row) System.out.printf("%10.4f ", val);
            System.out.println();
        }
    }
}