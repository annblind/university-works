package semester1.practice08.homework;

class Matrix2D {
    protected double[][] data;
    private int rows;
    private int cols;

    public Matrix2D(double[][] source) {
        if (source == null || source.length == 0
                || source[0] == null || source[0].length == 0) {
            throw new IllegalArgumentException("Матриця не може бути порожньою!");
        }

        rows = source.length;
        cols = source[0].length;
        data = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            if (source[i] == null || source[i].length != cols) {
                throw new IllegalArgumentException(
                        "Некоректна матриця!");
            }

            data[i] = java.util.Arrays.copyOf(source[i], cols);
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public double getElement(int r, int c) {
        checkBounds(r, c);
        return data[r][c];
    }

    public void setElement(int r, int c, double val) {
        checkBounds(r, c);
        data[r][c] = val;
    }

    private void checkBounds(int r, int c) {
        if (r < 0 || r >= rows || c < 0 || c >= cols) {
            throw new IndexOutOfBoundsException(
                    "Індекс виходить за межі матриці!");
        }
    }

    public double getSum() {
        double sum = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sum += data[i][j];
            }
        }

        return sum;
    }

    public double getMaxElement() {
        double max = data[0][0];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (data[i][j] > max) {
                    max = data[i][j];
                }
            }
        }

        return max;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < rows; i++) {
            result.append("[ ");

            for (int j = 0; j < cols; j++) {
                result.append(String.format("%8.2f", data[i][j]));
                result.append(" ");
            }

            result.append("]\n");
        }

        return result.toString();
    }
}
class SquareMatrix extends Matrix2D {
    public SquareMatrix(double[][] source) {
        super(source);

        if (getRows() != getCols()) {
            System.out.println(
                    "Попередження: матриця не є квадратною!");
        }
    }

    public int getDimension() {
        return getRows();
    }

    public double getTrace() {
        if (getRows() != getCols()) {
            return 0.0;
        }

        double trace = 0;

        for (int i = 0; i < getDimension(); i++) {
            trace += data[i][i];
        }

        return trace;
    }

    public boolean isSymmetric() {
        if (getRows() != getCols()) {
            return false;
        }

        for (int i = 0; i < getDimension(); i++) {
            for (int j = i + 1; j < getDimension(); j++) {
                if (Math.abs(data[i][j] - data[j][i]) > 1e-9) {
                    return false;
                }
            }
        }

        return true;
    }

    public double getDeterminant() {
        int n = getDimension();

        if (getRows() != getCols()) {
            return 0.0;
        }

        if (n == 2) {
            return data[0][0] * data[1][1]
                    - data[0][1] * data[1][0];
        }

        if (n == 3) {
            return data[0][0] * data[1][1] * data[2][2]
                    + data[0][1] * data[1][2] * data[2][0]
                    + data[0][2] * data[1][0] * data[2][1]
                    - data[0][2] * data[1][1] * data[2][0]
                    - data[0][1] * data[1][0] * data[2][2]
                    - data[0][0] * data[1][2] * data[2][1];
        }

        return 0.0;
    }

    @Override
    public String toString() {
        return super.toString()
                + String.format(
                "Характеристики SquareMatrix: Розмір = %dx%d"
                        + " | Слід (Trace) = %.2f"
                        + " | Симетрична = %b"
                        + " | det(A) = %.2f\n",
                getRows(),
                getCols(),
                getTrace(),
                isSymmetric(),
                getDeterminant()
        );
    }
}

class CramerMatrixSolver extends SquareMatrix {
    private double[] freeTerms;

    public CramerMatrixSolver(double[][] coefficients,
                              double[] freeTerms) {
        super(coefficients);

        if (getRows() != getCols()) {
            throw new IllegalArgumentException(
                    "Матриця повинна бути квадратною!");
        }

        if (freeTerms == null
                || freeTerms.length != getDimension()) {
            throw new IllegalArgumentException(
                    "Некоректна кількість вільних членів!");
        }

        this.freeTerms =
                java.util.Arrays.copyOf(freeTerms, freeTerms.length);
    }

    public double[] solve() {
        int n = getDimension();

        if (n != 2 && n != 3) {
            System.out.println(
                    "Метод Крамера підтримує матриці 2x2 та 3x3!");
            return new double[0];
        }

        double mainDeterminant = getDeterminant();

        if (Math.abs(mainDeterminant) < 1e-9) {
            System.out.println(
                    "Система не має єдиного розв'язку!");
            return new double[0];
        }

        double[] solutions = new double[n];

        for (int col = 0; col < n; col++) {
            double[][] replacedMatrix = new double[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (j == col) {
                        replacedMatrix[i][j] = freeTerms[i];
                    } else {
                        replacedMatrix[i][j] = data[i][j];
                    }
                }
            }

            SquareMatrix helper =
                    new SquareMatrix(replacedMatrix);

            solutions[col] =
                    helper.getDeterminant() / mainDeterminant;
        }

        return solutions;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("Розширена матриця системи [A | B]:\n");

        for (int i = 0; i < getDimension(); i++) {
            result.append("[ ");

            for (int j = 0; j < getDimension(); j++) {
                result.append(String.format("%8.2f", data[i][j]));
                result.append(" ");
            }

            result.append("| ");
            result.append(String.format("%8.2f", freeTerms[i]));
            result.append(" ]\n");
        }

        result.append(String.format(
                "Головний визначник det(A) = %.2f\n",
                getDeterminant()
        ));

        double[] solutions = solve();

        if (solutions.length > 0) {
            result.append("Розв'язок системи: ");

            for (int i = 0; i < solutions.length; i++) {
                result.append(String.format(
                        "x%d = %.2f", i + 1, solutions[i]));

                if (i < solutions.length - 1) {
                    result.append(", ");
                }
            }

            result.append("\n");
        } else {
            result.append("Єдиного розв'язку немає.\n");
        }

        return result.toString();
    }
}
public class Task4MatrixHierarchy {
    public static void main(String[] args) {

        System.out.println(
                "================ ІЄРАРХІЯ МАТРИЦЬ ТА СЛАР ================");

        System.out.println(
                "\n--- 1. Квадратна матриця 3x3 (SquareMatrix) ---");

        double[][] squareData = {
                {2, 1, 0},
                {1, 3, 1},
                {0, 1, 2}
        };

        SquareMatrix squareMatrix =
                new SquareMatrix(squareData);

        System.out.println(squareMatrix);

        System.out.println(
                "\n--- 2. Розв'язувач системи Крамера (CramerMatrixSolver 2x2) ---");

        double[][] coefficients = {
                {2, 3},
                {4, -1}
        };

        double[] freeTerms = {8, 2};

        CramerMatrixSolver solver =
                new CramerMatrixSolver(coefficients, freeTerms);

        System.out.println(solver);

        double[] roots = solver.solve();

        if (roots.length == 2) {
            double check1 = 2 * roots[0] + 3 * roots[1];
            double check2 = 4 * roots[0] - roots[1];

            if (Math.abs(check1 - 8) < 1e-9
                    && Math.abs(check2 - 2) < 1e-9) {
                System.out.println("Перевірка пройдена!");
            } else {
                System.out.println("Помилка перевірки!");
            }
        }

        System.out.println(
                "==========================================================");
    }
}