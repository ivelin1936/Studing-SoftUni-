package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CensorEmailAddress {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String email = reader.readLine();
        String[] emailForCensore = email.split("@");
        String textForCensore = reader.readLine();

        int count = emailForCensore[0].trim().length();
        String censore = new String(new char[count]).replace("\0", "*");

        StringBuilder sb = new StringBuilder();
        sb.append(censore).append("@").append(emailForCensore[1].trim());

        String censoredEmail = sb.toString();

        System.out.println(textForCensore.replaceAll(email, censoredEmail));
    }
}