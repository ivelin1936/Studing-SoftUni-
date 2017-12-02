package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VariableInHexFormat {

    public static void main(String[] args) throws IOException {
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	int result = Integer.parseInt(reader.readLine(), 16);

        System.out.println(result);
    }
}
