public class Lab1 {
    public static void main(String[] args) {
        // Межі сумування (згідно з C7=0 тип обов'язково byte)
        byte a = 2;
        byte n = 5;
        byte b = 1;
        byte m = 3;

        double totalSum = 0; // Для збереження точного дійсного результату

        System.out.println("Starting double sum calculation...");

        for (byte i = a; i <= n; i++) {
            // Захист від ділення на нуль: знаменник (i - 1) стає 0 при i = 1
            if (i == 1) {
                System.err.println("Warning: Division by zero when i = 1. Step skipped.");
                continue; 
            }

            for (byte j = b; j <= m; j++) {
                // Додатковий захист від ділення за модулем на нуль (якщо межа b = 0)
                if (j == 0) {
                    System.err.println("Warning: Modulo division by j = 0. Step skipped.");
                    continue;
                }

                // Явне приведення до double для виконання точного ділення
                double term = (double) (i % j) / (i - 1);
                totalSum += term;
            }
        }

        System.out.println("Calculation completed successfully!");
        System.out.printf("Final sum S = %.4f\n", totalSum);
    }
}