package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReverseCharacters {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = "";
        for (int i = 0; i < 3; i++) {
            input = input + reader.readLine();
        }

        StringBuilder sb = new StringBuilder(input);
        System.out.println(sb.reverse().toString());
    }
}
