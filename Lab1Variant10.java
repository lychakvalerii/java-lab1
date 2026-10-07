import java.util.Scanner;
import java.util.Locale;

public class Lab1Variant10 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        int choice = -1;

        while (choice != 0) {
            System.out.println();
            System.out.println("Меню лабораторної роботи:");
            System.out.println("1. Завдання 1 (Обчислення виразу)");
            System.out.println("2. Завдання 2 (Симетрична різниця масивів)");
            System.out.println("3. Завдання 3 (Симетричні послідовності в матриці)");
            System.out.println("4. Завдання 4 (Вилучення слів)");
            System.out.println("0. Вихід");
            System.out.print("Оберіть номер завдання: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                System.out.println();

                switch (choice) {
                    case 1:
                        task1(scanner);
                        break;
                    case 2:
                        task2(scanner);
                        break;
                    case 3:
                        task3(scanner);
                        break;
                    case 4:
                        task4(scanner);
                        break;
                    case 0:
                        System.out.println("Завершення роботи.");
                        break;
                    default:
                        System.out.println("Немає такого варіанту, спробуй ще раз.");
                }
            } else {
                System.out.println("Введіть коректне число.");
                scanner.next();
            }
        }

        scanner.close();
    }

    public static void task1(Scanner scanner) {
        System.out.print("Введіть a (дійсне): ");
        double aReal = scanner.nextDouble();
        System.out.print("Введіть b (дійсне): ");
        double bReal = scanner.nextDouble();

        System.out.print("Введіть a (ціле): ");
        int aInt = scanner.nextInt();
        System.out.print("Введіть b (ціле): ");
        int bInt = scanner.nextInt();

        double res1 = (1.0 / (aReal * Math.pow(bReal, 2) - 2 * bReal)) + (1.0 / (3 * Math.pow(aReal, 2) + 2)) - bReal;
        System.out.printf("1 (дійсні -> дійсне): %.4f\n", res1);

        double res2 = (1.0 / (aInt * Math.pow(bInt, 2) - 2 * bInt)) + (1.0 / (3 * Math.pow(aInt, 2) + 2)) - bInt;
        System.out.printf("2 (цілі -> дійсне): %.4f\n", res2);

        int res3 = (int) Math.round((1.0 / (aReal * Math.pow(bReal, 2) - 2 * bReal)) + (1.0 / (3 * Math.pow(aReal, 2) + 2)) - bReal);
        System.out.printf("3 (дійсні -> ціле): %d\n", res3);
    }

    public static void task2(Scanner scanner) {
        System.out.print("Введіть розмір масиву A (n): ");
        int n = scanner.nextInt();
        int[] A = new int[n];
        System.out.println("Введіть елементи A:");
        for (int i = 0; i < n; i++) A[i] = scanner.nextInt();

        System.out.print("Введіть розмір масиву B (m): ");
        int m = scanner.nextInt();
        int[] B = new int[m];
        System.out.println("Введіть елементи B:");
        for (int i = 0; i < m; i++) B[i] = scanner.nextInt();

        int[] C = new int[n + m];
        int cIndex = 0;

        for (int i = 0; i < n; i++) {
            if (!contains(B, B.length, A[i]) && !contains(C, cIndex, A[i])) {
                C[cIndex++] = A[i];
            }
        }

        for (int i = 0; i < m; i++) {
            if (!contains(A, A.length, B[i]) && !contains(C, cIndex, B[i])) {
                C[cIndex++] = B[i];
            }
        }

        System.out.print("Симетрична різниця (Масив C): ");
        for (int i = 0; i < cIndex; i++) {
            System.out.print(C[i] + " ");
        }
        System.out.println();
    }

    private static boolean contains(int[] arr, int length, int val) {
        for (int i = 0; i < length; i++) {
            if (arr[i] == val) return true;
        }
        return false;
    }

    public static void task3(Scanner scanner) {
        System.out.print("Введіть розмірність матриці n: ");
        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];

        System.out.println("Введіть елементи матриці:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        System.out.print("Номери рядків із симетричними послідовностями: ");
        boolean found = false;
        for (int i = 0; i < n; i++) {
            boolean isSymmetric = true;
            for (int j = 0; j < n / 2; j++) {
                if (matrix[i][j] != matrix[i][n - 1 - j]) {
                    isSymmetric = false;
                    break;
                }
            }
            if (isSymmetric) {
                System.out.print((i + 1) + " ");
                found = true;
            }
        }
        if (!found) System.out.print("Немає таких рядків.");
        System.out.println();
    }

    public static void task4(Scanner scanner) {
        scanner.nextLine();
        System.out.println("Введіть текст:");
        String text = scanner.nextLine();

        String[] words = text.split("\\b");
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (word.matches("[\\p{L}]+")) {
                if (!hasRepeatingLetters(word)) {
                    result.append(word);
                }
            } else {
                result.append(word);
            }
        }

        System.out.println("Текст після вилучення слів: " + result.toString().replaceAll(" +", " ").trim());
    }

    private static boolean hasRepeatingLetters(String word) {
        String lowerWord = word.toLowerCase();
        for (int i = 0; i < lowerWord.length(); i++) {
            for (int j = i + 1; j < lowerWord.length(); j++) {
                if (lowerWord.charAt(i) == lowerWord.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }
}