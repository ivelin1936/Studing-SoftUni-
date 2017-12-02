package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class MostFrequentNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] inputArr = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        LinkedHashMap<Integer, Integer> numsMap = new LinkedHashMap<>();

        for (int num : inputArr) {
            numsMap.putIfAbsent(num, 1);

            if (numsMap.containsKey(num)) {
                int count = numsMap.get(num) + 1;
                numsMap.replace(num, count);
            }
        }

        int max = -1;
        int mostFrequent = -1;

        for (Integer num : numsMap.keySet()) {
            if (numsMap.get(num) > max) {
                mostFrequent = num;
                max = numsMap.get(num);
            }
        }

        System.out.println(mostFrequent);
    }
}
