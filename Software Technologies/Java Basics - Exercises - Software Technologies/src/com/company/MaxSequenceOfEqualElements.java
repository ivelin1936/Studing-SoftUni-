package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MaxSequenceOfEqualElements {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] numbers = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int bestCount = 0;
        int num = 0;
        int br = 1;
        int buffNum = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] == numbers[i - 1]) {
                br++;

                if (br > bestCount) {
                    bestCount = br;
                    num = buffNum;
                }
            } else {
                buffNum = numbers[i];
                br = 1;
            }
        }

        for (int i = 0; i < bestCount; i++) {
            System.out.print(num + " ");
        }
    }
}
