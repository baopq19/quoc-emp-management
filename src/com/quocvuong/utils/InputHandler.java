package com.quocvuong.utils;

import java.math.BigDecimal;
import java.util.Scanner;

public class InputHandler {
    public static int getPositiveIntegerInput() {
        Scanner scanner = new Scanner(System.in);
        String userInputString = scanner.nextLine();
        int result = -1;
        try {
            result = Integer.parseInt(userInputString);
        } catch (Exception e) {
            System.out.println("Please input number!!!");
        }
        if (result > 0) return result;
        return -1;
    }

    public static String getStringInput() {
        Scanner scanner = new Scanner(System.in);
        String userInputString = scanner.nextLine();
        return userInputString;
    }
}
