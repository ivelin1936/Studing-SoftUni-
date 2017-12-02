package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class BombNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<Integer> numbers = InputTolist(reader.readLine().split("\\s"));

        int[] boomber = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int bomb = boomber[0];
        int power = boomber[1];

        while (numbers.indexOf(bomb) >= 0){
            Explode(numbers, bomb, power);
        }

        System.out.println(Sum(numbers));
    }

    private static int Sum(ArrayList<Integer> numbers) {
        int sum = 0;

        for (int i = 0; i < numbers.size(); i++) {
            sum += numbers.get(i);
        }

        return sum;
    }

    private static void Explode(ArrayList<Integer> numbers, int bomb, int power) {
        int bombIndex = numbers.indexOf(bomb);
        int removalIndex = bombIndex - power;
        int deletionsCount = power * 2 + 1;

        if (removalIndex < 0) {
            deletionsCount += removalIndex;
            removalIndex = 0;
        }

        while (deletionsCount > 0 && removalIndex < numbers.size()) {
            numbers.remove(removalIndex);
            deletionsCount--;
        }
    }

    private static ArrayList<Integer> InputTolist(String[] strings) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();

        for (int i = 0; i < strings.length; i++) {
            numbers.add(Integer.parseInt(strings[i]));
        }

        return numbers;
    }
}
