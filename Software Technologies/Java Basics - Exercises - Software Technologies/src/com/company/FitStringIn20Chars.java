package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FitStringIn20Chars {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();

        if (input.length() < 20) {
            int difference = 20 - input.length();
            for (int i = 0; i < difference; i++) {
                input = input + "*";
            }
            System.out.println(input);
        } else if (input.length() > 20) {
            System.out.println(input.substring(0, 20));
        } else {
            System.out.println(input);
        }
    }
}
