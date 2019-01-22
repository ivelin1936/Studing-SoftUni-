package p01_ParsingHttpRequests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AppDemo {

    private static final String DEFAULT_404_BODY = "The requested functionality was not found.";
    private static final String DEFAULT_401_BODY = "You are not authorized to access the requested functionality.";
    private static final String DEFAULT_400_BODY_POST = "There was an error with the requested functionality due to malformed request.";
    private static final String DEFAULT_400_BODY_GET = "Greetings %s!";
    private static final String DEFAULT_200_OK_BODY = "Greetings %s! You have successfully created %s with %s - %s, %s - %s.";
    private static final String DEFAULT_HTTP_RES_VERSION = "HTTP/1.1";
    private static final String DEFAULT_HEADERS_PATTERN = "%s: %s";
    private static final String AUTH = "Authorization";
    private static final String PROCESS_HEADER_DATE = "Date";
    private static final String PROCESS_HEADER_HOST = "Host";
    private static final String PROCESS_HEADER_CONTENT_TYPE = "Content-Type";
    private static final String BAD_REQUEST = "400 Bad Request";
    private static final String UNAUTHORIZED = "401 Unauthorized";
    private static final String NOT_FOUND = "404 Not Found";
    private static final String STATUS_OK = "200 OK";
    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String STATUS_CODE_200 = "200";
    private static final String STATUS_CODE_400 = "400";
    private static final String STATUS_CODE_401 = "401";
    private static final String STATUS_CODE_404 = "404";

    private static BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        /** Read valid urls */
        List<String> validUrls = getValidUrls();
        /** read request */
        List<String> request = getRequest();
        /** parse request */
        String method = getMethod(request.get(0));
        String url = getURL(request.get(0));
//        String protocolVersion = getProtocol(request.get(0));
        Map<String, String> headers = getHeaders(request);
        Map<String, String> bodyParams = getBodyParams(request);

        /** Build response */
        String status = responseStatus(validUrls, method, url, headers, bodyParams);
        String response = getRespond(method, headers, bodyParams, status);

        System.out.println(response);

    }

    private static String getRespond(String method, Map<String, String> headers, Map<String, String> bodyParams, String status) {
        StringBuilder response = new StringBuilder();
        String res;
        switch (status) {
            case STATUS_CODE_200:
                res = buildResponse(headers, STATUS_OK);
                response.append(res)
                        .append(System.lineSeparator());

                String username = getUsername(headers.get(AUTH));
                if (GET.equalsIgnoreCase(method)) {
                    response.append(String.format(DEFAULT_400_BODY_GET, username));
                } else {
                    List<String> paramsKeySet = new ArrayList<>(bodyParams.keySet());
                    response.append(
                            String.format(DEFAULT_200_OK_BODY,
                                    username,
                                    bodyParams.get(paramsKeySet.get(0)),
                                    paramsKeySet.get(1),
                                    bodyParams.get(paramsKeySet.get(1)),
                                    paramsKeySet.get(2),
                                    bodyParams.get(paramsKeySet.get(2))
                            ));
                }
                break;
            case STATUS_CODE_400:
                res = buildResponse(headers, BAD_REQUEST);
                response.append(res)
                        .append(System.lineSeparator())
                        .append(DEFAULT_400_BODY_POST);
                break;
            case STATUS_CODE_401:
                res = buildResponse(headers, UNAUTHORIZED);
                response.append(res)
                        .append(System.lineSeparator())
                        .append(DEFAULT_401_BODY);
                break;
            case STATUS_CODE_404:
                res = buildResponse(headers, NOT_FOUND);
                response.append(res)
                        .append(System.lineSeparator())
                        .append(DEFAULT_404_BODY);
                break;
        }

        return response.toString();
    }

    private static String buildResponse(Map<String, String> headers, String status) {
        StringBuilder resBuilder = new StringBuilder();
        resBuilder.append(String.format("%s %s", DEFAULT_HTTP_RES_VERSION, status))
                .append(System.lineSeparator());

        headers.forEach((k, v) -> {
            if (PROCESS_HEADER_DATE.equalsIgnoreCase(k)
                    || PROCESS_HEADER_HOST.equalsIgnoreCase(k)
                    || PROCESS_HEADER_CONTENT_TYPE.equalsIgnoreCase(k)) {
                resBuilder.append(String.format(DEFAULT_HEADERS_PATTERN, k, v))
                        .append(System.lineSeparator());
            }
        });

        return resBuilder.toString();
    }

    private static String getUsername(String authorization) {
        String base64 = authorization.split("\\s+")[1];
        byte[] valueDecoded = Base64.getDecoder().decode(base64);

        return new String(valueDecoded);
    }

    private static String responseStatus(List<String> validUrls, String method, String url, Map<String, String> headers, Map<String, String> bodyParams) {
        if (!validUrls.contains(url)) {
            return STATUS_CODE_404;
        } else if (!headers.containsKey(AUTH)) {
            return STATUS_CODE_401;
        } else if (POST.equalsIgnoreCase(method) && bodyParams == null) {
            return STATUS_CODE_400;
        } else {
            return STATUS_CODE_200;
        }
    }

    private static String getProtocol(String line) {
        String[] lineTokens = line.split("\\s+");
        return lineTokens[lineTokens.length - 1];
    }

    private static List<String> getValidUrls() throws IOException {
        return Arrays.asList(reader.readLine().split("\\s+"));
    }

    private static String getMethod(String line) {
        return line.split("\\s+")[0];
    }

    private static String getURL(String line) {
        return line.split("\\s+")[1];
    }

    private static Map<String, String> getHeaders(List<String> requestHeaders) {
        Map<String, String> headers = new LinkedHashMap<>();

        requestHeaders.stream()
                .filter(h -> h.contains(": "))
                .map(header -> header.split(": "))
                .forEach(header -> headers.put(header[0], header[1]));

        return headers;
    }

    private static Map<String, String> getBodyParams(List<String> request) {
        Map<String, String> bodyParams = new LinkedHashMap<>();

        if (request.get(request.size() - 1).equals("\r\n")) {
            return null;
        }

        Arrays.stream(request.get(request.size() - 1)
                .split("&"))
                .map(bp -> bp.split("="))
                .forEach(bpKvp -> bodyParams.put(bpKvp[0], bpKvp[1]));

        return bodyParams;
    }

    private static List<String> getRequest() throws IOException {
        List<String> request = new ArrayList<>();

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.length() == 0) {
                request.add(System.lineSeparator());

                if (reader.ready()) {
                    if ((line = reader.readLine()) != null && line.length() > 0) {
                        request.add(line);
                    }
                }
                break;
            }
            request.add(line);
        }
        return request;
    }
}
