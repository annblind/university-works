package semester1.practice06.homework;

class MatrixSolver {
    private double[][] data;
    public void setMatrix(double[][] data) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("Матриця не може бути порожньою!");
        }

        int columns = data[0].length;

        if (columns == 0) {
            throw new IllegalArgumentException("Матриця не може бути порожньою!");
        }

        for (double[] row : data) {
            if (row == null || row.length != columns) {
                throw new IllegalArgumentException("Некоректна матриця!");
            }
        }

        this.data = new double[data.length][columns];

        for (int i = 0; i < data.length; i++) {
            this.data[i] = data[i].clone();
        }
    }
    public void printMatrix() {
        checkMatrix();

        for (double[] row : data) {
            System.out.print("[ ");
            for (double value : row) {
                System.out.printf("%7.2f ", value);
            }
            System.out.println("]");
        }
    }
    private void checkSize(int size) {
        checkMatrix();

        if (data.length != size || data[0].length != size) {
            throw new IllegalStateException(
                    "Матриця повинна мати розмір " + size + "x" + size
            );
        }
    }

    private void checkMatrix() {
        if (data == null) {
            throw new IllegalStateException("Матрицю не встановлено!");
        }
    }
    public double getDeterminant2x2() {
        checkSize(2);

        return data[0][0] * data[1][1]
                - data[0][1] * data[1][0];
    }

    public double getDeterminant3x3() {
        checkSize(3);

        return data[0][0] * data[1][1] * data[2][2]
                + data[0][1] * data[1][2] * data[2][0]
                + data[0][2] * data[1][0] * data[2][1]
                - data[0][2] * data[1][1] * data[2][0]
                - data[0][0] * data[1][2] * data[2][1]
                - data[0][1] * data[1][0] * data[2][2];
    }

    public void transpose() {
        checkMatrix();

        int rows = data.length;
        int columns = data[0].length;

        double[][] transposed = new double[columns][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                transposed[j][i] = data[i][j];
            }
        }

        data = transposed;
    }

    public void solveSystem2x2(double c1, double c2) {
        checkSize(2);

        double delta = getDeterminant2x2();

        double deltaX = c1 * data[1][1] - c2 * data[0][1];

        double deltaY = data[0][0] * c2 - data[1][0] * c1;

        System.out.printf("Головний визначник Delta = %.2f%n", delta);
        System.out.printf("Визначник Delta_x = %.2f%n", deltaX);
        System.out.printf("Визначник Delta_y = %.2f%n", deltaY);

        if (Math.abs(delta) > 1e-9) {
            double x = deltaX / delta;
            double y = deltaY / delta;

            System.out.println("\nЗнайдено єдиний розв'язок:");
            System.out.printf("x = %.2f%n", x);
            System.out.printf("y = %.2f%n", y);

            System.out.printf(
                    "Перевірка: %.2f*x + %.2f*y = %.2f%n",
                    data[0][0], data[0][1],
                    data[0][0] * x + data[0][1] * y
            );

        } else if (Math.abs(deltaX) < 1e-9
                && Math.abs(deltaY) < 1e-9) {

            System.out.println("Система має безліч розв'язків");

        } else {
            System.out.println("Система несумісна (не має розв'язків)");
        }
    }
}
public class Task4MatrixSolver {
    public static void main(String[] args) {

        System.out.println(
                "================ МАТРИЧНИЙ КАЛЬКУЛЯТОР ТА СЛАР ================"
        );

        MatrixSolver solver = new MatrixSolver();

        double[][] matrix2x2 = {
                {2, 3},
                {4, -1}
        };

        solver.setMatrix(matrix2x2);

        System.out.println("\nПочаткова матриця 2x2:");
        solver.printMatrix();

        double determinant = solver.getDeterminant2x2();

        System.out.printf(
                "\nВизначник det(A) = %.2f%n",
                determinant
        );

        solver.transpose();

        System.out.println("\nТранспонована матриця A^T:");
        solver.printMatrix();

        System.out.println(
                "\n--- РОЗВ'ЯЗАННЯ СИСТЕМИ МЕТОДОМ КРАМЕРА ---"
        );

        double[][] systemMatrix = {
                {2, 3},
                {4, -1}
        };

        solver.setMatrix(systemMatrix);

        System.out.println("Рівняння:");
        System.out.println("  2x + 3y = 8");
        System.out.println("  4x - y = 2\n");

        solver.solveSystem2x2(8, 2);

        System.out.println("\n--- МАТРИЦЯ 3x3 ---");

        double[][] matrix3x3 = {
                {1, 2, 3},
                {0, 1, 4},
                {5, 6, 0}
        };

        solver.setMatrix(matrix3x3);

        System.out.println("Матриця 3x3:");
        solver.printMatrix();

        System.out.printf(
                "\nВизначник det(A) = %.2f%n",
                solver.getDeterminant3x3()
        );

        System.out.println(
                "\n==============================================================="
        );
    }
}
