/*
Write a Java program, which receives a number n, as a string array with a single element, and print all symmetrical
numbers in the range [1…n].
 */
package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SymmetricNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] numArr = new String[1];
        numArr[0] = reader.readLine();
        int num = Integer.parseInt(numArr[0]);

        for (int i = 1; i <= num; i++) {
            String val = String.valueOf(i);
            if (isSimmetric(val)) {
                System.out.print(i + " ");
            }
        }
    }

    private static boolean isSimmetric(String number) {
        boolean isSimmetric = false;

        StringBuilder sb = new StringBuilder(number);
        if (sb.reverse().toString().equals(number)) {
            isSimmetric = true;
        }

        return isSimmetric;
    }
}
