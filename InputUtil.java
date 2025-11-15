package com.expensetracker.util;

import java.util.Scanner;

public class InputUtil {
    private static Scanner sc = new Scanner(System.in);

    public static String getString(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    public static int getInt(String msg) {
        System.out.print(msg);
        return Integer.parseInt(sc.nextLine());
    }

    public static double getDouble(String msg) {
        System.out.print(msg);
        return Double.parseDouble(sc.nextLine());
    }
}
