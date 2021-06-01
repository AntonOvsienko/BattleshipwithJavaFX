package ua.com.finaly;

import java.util.Scanner;

public class DataInput {
    private static Scanner scanner = new Scanner(System.in);

    public static Integer InputInteger() {
        while (true) {
            try {
                String xtemp = scanner.nextLine();
                return Integer.parseInt(xtemp);
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели не число");
                continue;
            }
        }
    }

    public static Integer InputInteger(int size) {
        while (true) {
            try {
                String xtemp = scanner.nextLine();
                int x = Integer.parseInt(xtemp);
                if (x > size) {
                    System.out.println("Число вне диапазона");
                    continue;
                } else {
                    return x;
                }
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели не число");
                continue;
            }
        }
    }

    public static String InputString() {
        while (true) {
            try {
                String xtemp = scanner.nextLine();
                return xtemp;
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели не число");
                continue;
            }
        }
    }
}
