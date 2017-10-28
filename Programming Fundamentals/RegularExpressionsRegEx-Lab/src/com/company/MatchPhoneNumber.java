package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchPhoneNumber {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String toCheck = reader.readLine();

        Pattern pattern = Pattern.compile("(\\+[0-9]{3})([-| ])[0-9]\\2[0-9]{3}\\2[1-9]{4}\\b");
        Matcher matcher = pattern.matcher(toCheck);

        Set<String> result = new HashSet<>();

        while (matcher.find()) {
            String phoneNumber = matcher.group();
            result.add(phoneNumber.trim());
        }
        System.out.println(String.join(", ", result));
    }
}
