package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChangeToUppercase {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input = reader.readLine();
        StringBuilder sb = new StringBuilder();

        int indxF = input.indexOf("<upcase>");
        int indxS = input.indexOf("</upcase>");

        while (indxF != -1) {
            sb.append(input.substring(sb.length(), indxF));
            sb.append(input.substring(indxF + 8, indxS).toUpperCase());

            input = input.replaceFirst("<upcase>", "");
            input = input.replaceFirst("</upcase>", "");

            indxF = input.indexOf("<upcase>");
            indxS = input.indexOf("</upcase>");
        }

        if (sb.length() < input.length()) {
            sb.append(input.substring(sb.length(), input.length()));
        }

        System.out.println(sb.toString());
    }
}
