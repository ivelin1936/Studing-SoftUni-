package com.company;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.util.Arrays;

public class IntegersSum {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] numbersArr = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int num1 = numbersArr[0];
        int num2 = numbersArr[1];
        int num3 = numbersArr[2];

        if (num1 + num2 == num3) {
            System.out.printf("%d + %d = %d", Math.min(num1, num2), Math.max(num1, num2), num3);
        } else if (num1 + num3 == num2) {
            System.out.printf("%d + %d = %d", Math.min(num1, num3), Math.max(num1, num3), num2);
        } else if (num2 + num3 == num1) {
            System.out.printf("%d + %d = %d", Math.min(num2, num3), Math.max(num2, num3), num1);
        } else {
            System.out.println("No");
        }
    }
}
