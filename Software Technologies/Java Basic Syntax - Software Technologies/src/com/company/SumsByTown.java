package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class SumsByTown {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        TreeMap<String, Double> townIncome = new TreeMap<>();

        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String[] inputArr = reader.readLine().split(" \\| ");

            updateTownIncomeMap(townIncome, inputArr);
        }

        for (String town : townIncome.keySet()) {
            System.out.println(town + " -> " + townIncome.get(town));
        }
    }

    private static void updateTownIncomeMap(TreeMap<String, Double> townIncome, String[] inputArr)
    {
        String town = inputArr[0].trim();
        double income = Double.parseDouble(inputArr[1]);

        if (!townIncome.containsKey(town)) {
            townIncome.put(town, income);
        } else if (townIncome.containsKey(town)){
            double value = townIncome.get(town);
            value += income;
            townIncome.replace(town, value);
        }
    }
}
