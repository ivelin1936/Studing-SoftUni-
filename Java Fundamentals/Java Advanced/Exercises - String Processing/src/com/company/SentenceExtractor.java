package com.company;
// 20/100 in judge
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceExtractor {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String keyWord = reader.readLine();
        String text = reader.readLine();

        Pattern pattern = Pattern.compile("[A-Za-z,;'’\\\\\"\\s]+[.?!]");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String sentence = matcher.group();
            if (sentence.contains(" is ") || sentence.contains("Is ")) {
                System.out.println(sentence.trim());
            }
        }
    }
}
