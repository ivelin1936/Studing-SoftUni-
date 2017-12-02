package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class IndexOfLetters {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String word = reader.readLine();

        List<Character> lettersList = new ArrayList<>();
        for (char i = 'a'; i <= 'z'; i++) {
            lettersList.add(i);
        }

        for (int i = 0; i < word.length(); i++) {
            System.out.println(word.charAt(i) + " -> " + lettersList.indexOf(word.charAt(i)));
        }
    }
}
