package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxSequenceOfIncreasingElements {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] numbers = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        List<Integer> resultList = new ArrayList<>();
        List<Integer> buffList = new ArrayList<>();

        buffList.add(numbers[0]);
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > (numbers[i - 1])) {
                buffList.add(numbers[i]);
            } else {
                buffList.clear();
                buffList.add(numbers[i]);
            }

            if (buffList.size() > resultList.size()) {
                resultList.clear();
                resultList.addAll(buffList);
            }
        }

        for (Integer num : resultList) {
            System.out.print(num + " ");
        }
    }
}
