package p01_extractingCookies;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppDemo {

    private static final String COOKIE = "Cookie";

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        List<String> request = readRequest();

        request.stream()
                .filter(line -> line.contains(": "))
                .forEach(header -> {
                    if (header.startsWith(COOKIE)) {
                        String cookies = header.split(": ")[1];

                        Arrays.stream(cookies.split("; "))
                                .forEach(c -> {
                                    String[] cookieTokens = c.split("=");

                                    System.out.println(String.format("%s <-> %s", cookieTokens[0], cookieTokens[1]));
                                });
                    }
                });
    }

    private static List<String> readRequest() throws IOException {
        List<String> req = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
            req.add(line);
        }

        req.add(System.lineSeparator());
        if (reader.ready()) {
            if ((line = reader.readLine()) != null && line.length() > 0) {
                req.add(line);
            }
        }

        return req;
    }
}
