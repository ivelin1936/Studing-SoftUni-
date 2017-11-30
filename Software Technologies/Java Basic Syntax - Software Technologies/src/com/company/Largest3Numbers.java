package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Largest3Numbers {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] numbersArr = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Arrays.sort(numbersArr);

        if (numbersArr.length >= 3) {
            for (int i = numbersArr.length - 1; i >= numbersArr.length - 3; i--) {
                System.out.println(numbersArr[i] + " ");
            }
        } else {
            for (int i = numbersArr.length - 1; i >= 0 ; i--) {
                System.out.println(numbersArr[i] + " ");
            }
        }
    }
}
