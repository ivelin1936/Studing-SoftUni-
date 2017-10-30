package com.company;
// 63/100 in Judge
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QueryMess {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<String> myList = new ArrayList<>(Arrays.asList(reader.readLine().split("&")));

        Pattern pattern = Pattern.compile(
                "((\\b[A-Za-z0-9_\\-.*]+\\b(\\+)?)(=)(?:\\+|[%20])?(\\b(?:[0-9]{2})?(\\w+)(?:(\\+)?([%20])?(\\+)?)?\\w+(\\+)?)?(\\d+)?((://\\w+.\\w+)?(/\\w+)?(/\\w+)?(/\\w+)?(/\\w+)?(/\\w+)?(/\\w+)?(/\\w+)?(/\\w+)?)?\\b)");
        while (!myList.get(0).equals("END"))
        {
            LinkedHashMap<String, List<String>> myMap = new LinkedHashMap<>();

            for (String str : myList) {
                Matcher matcher = pattern.matcher(str);

                if (matcher.find()) {
                    String matchStr = matcher.group();
                    List<String> matcherSplit = new ArrayList<>(Arrays.asList(matchStr.split("=")));

                    String keyWord = matcherSplit.get(0).trim();
                    if (keyWord.startsWith("+")) {
                        keyWord = keyWord.substring(1,keyWord.length());
                    } else if (keyWord.endsWith("+")) {
                        keyWord = keyWord.substring(0,keyWord.length() - 1);
                    }

                    if (!myMap.containsKey(keyWord)) {
                        myMap.put(keyWord, new ArrayList<>());
                    }

                    matchStr = matcherSplit.get(1).trim();

                    Pattern p = Pattern.compile("(?:%20)?(?:\\+)?([A-Za-z:/.\\-_0-9]+)(?:%20|\\+)?");
                    Matcher m = p.matcher(matchStr);
                    StringBuilder sb = new StringBuilder();
                    while (m.find()) {
                        sb.append(m.group(1) + " ");
                    }
                    myMap.get(keyWord).add(sb.toString().trim());
                }
            }

            for (String keyWord : myMap.keySet()) {
                System.out.print(keyWord + "=" + myMap.get(keyWord));
            }
            System.out.println();

            myMap.clear();
            myList = new ArrayList<>(Arrays.asList(reader.readLine().split("&")));
        }

    }
}

//**********Input*************
//login=student&password=student
//field=value1&field=value2&field=value3
//http://example.com/over/there?name=ferret
//foo=%20foo&value=+val&foo+=5+%20+203
//foo=poo%20&value=valley&dog=wow+
//url=https://softuni.bg/trainings/coursesinstances/details/1070
//https://softuni.bg/trainings.asp?trainer=nakov&course=oop&course=php
//END