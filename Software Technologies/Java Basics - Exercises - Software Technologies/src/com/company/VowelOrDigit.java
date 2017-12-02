package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VowelOrDigit {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();
        boolean isInteger = IsInteger(input);

        if (isInteger) {
            System.out.println("digit");
        } else {
            if (isVowel(input)) {
                System.out.println("vowel");
            } else {
                System.out.println("other");
            }
        }

    }

    private static boolean isVowel(String input)
    {
        boolean isVowel = false;

        if (input.equalsIgnoreCase("A")
                || input.equalsIgnoreCase("E")
                || input.equalsIgnoreCase("I")
                || input.equalsIgnoreCase("O")
                || input.equalsIgnoreCase("U")) {
            isVowel = true;
        }

        return isVowel;
    }

    private static boolean IsInteger(String input)
    {
        try {
            Integer.parseInt(input);
        } catch(NumberFormatException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}

