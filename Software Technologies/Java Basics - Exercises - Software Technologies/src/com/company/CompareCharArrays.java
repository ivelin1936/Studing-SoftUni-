package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CompareCharArrays {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        char[] array1 = reader.readLine().replaceAll(" ", "").toCharArray();
        char[] array2 = reader.readLine().replaceAll(" ", "").toCharArray();

        int min = Math.min(array1.length, array2.length);
        for (int i = 0; i < min; i++) {
            if (array1[i] < array2[i] && i < min - 1) {
                for (int j = 0; j < array1.length; j++) {
                    System.out.print(String.valueOf(array1[j]));
                }
                System.out.println();
                for (int j = 0; j < array2.length; j++) {
                    System.out.print(String.valueOf(array2[j]));
                }
                System.out.println();
                break;
            } else if (array1[i] > array2[i] && i < min - 1) {
                for (int j = 0; j < array2.length; j++) {
                    System.out.print(String.valueOf(array2[j]));
                }
                System.out.println();
                for (int j = 0; j < array1.length; j++) {
                    System.out.print(String.valueOf(array1[j]));
                }

                break;
            } else {
                if (i == min - 1 && array1.length == min) {
                    for (int j = 0; j < array1.length; j++) {
                        System.out.print(String.valueOf(array1[j]));
                    }
                    System.out.println();
                    for (int j = 0; j < array2.length; j++) {
                        System.out.print(String.valueOf(array2[j]));
                    }
                    System.out.println();
                } else if (i == min - 1 && array2.length == min) {
                    for (int j = 0; j < array2.length; j++) {
                        System.out.print(String.valueOf(array2[j]));
                    }
                    System.out.println();
                    for (int j = 0; j < array1.length; j++) {
                        System.out.print(String.valueOf(array1[j]));
                    }
                    System.out.println();
                }

            }
        }
    }
}
