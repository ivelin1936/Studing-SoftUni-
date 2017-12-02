package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class URLparser {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String url = reader.readLine();

        String[] protocolAndOther = url.split("://");

        if (protocolAndOther.length > 1) {
            String protocol = protocolAndOther[0].trim();
            System.out.println("[protocol] = \"" + protocol + "\"");

            String[] rigtPast = protocolAndOther[1].trim().split("/");
            serverAndResource(rigtPast);

        } else {
            System.out.println("[protocol] = \"\"");

            String[] rigtPast = protocolAndOther[0].trim().split("/");
            serverAndResource(rigtPast);
        }



    }

    private static void serverAndResource(String[] serverResourceArr) {
        if (serverResourceArr.length > 1)
        {
            System.out.println("[server] = \"" + serverResourceArr[0] + "\"");
            if (serverResourceArr.length > 2)
            {
                StringBuilder sb = new StringBuilder();
                sb.append(serverResourceArr[1]);
                for (int i = 2; i < serverResourceArr.length; i++)
                {
                    sb.append("/").append(serverResourceArr[i]);
                }
                System.out.println("[resource] = \"" + sb.toString() + "\"");
            } else {
                System.out.println("[resource] = \"" + serverResourceArr[1] + "\"");
            }
        } else {
            System.out.println("[server] = \"" + serverResourceArr[0] + "\"");
            System.out.println("[resource] = \"\"");
        }
    }
}
