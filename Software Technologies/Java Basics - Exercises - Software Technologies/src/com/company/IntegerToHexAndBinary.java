package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IntegerToHexAndBinary {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int intNumber = Integer.parseInt(reader.readLine());

        String hexDecimal = Integer.toHexString(intNumber).toUpperCase();
        String binary = Integer.toBinaryString(intNumber);

        System.out.println(hexDecimal);
        System.out.println(binary);
    }
}
