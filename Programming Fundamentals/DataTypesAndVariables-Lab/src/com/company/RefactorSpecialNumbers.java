package com.company;

import java.util.Scanner;

public class RefactorSpecialNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int number = Integer.parseInt(scanner.nextLine());
        int sum = 0;
        Boolean isSpecial = false;

        for (int i = 1; i <= number; i++)
        {
            sum += i % 10 + i / 10;

            if ((sum == 5) || (sum == 7) || (sum == 11)) {
                System.out.println(i  + " -> True");
            } else {
                System.out.println(i  + " -> " + isSpecial);
            }

            sum = 0;
        }
    }
}
