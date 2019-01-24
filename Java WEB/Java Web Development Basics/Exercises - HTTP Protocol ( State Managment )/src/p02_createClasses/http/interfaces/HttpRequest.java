package p02_createClasses.http.interfaces;

import java.util.List;
import java.util.Map;

public interface HttpRequest {

    List<HttpCookie> getCookies();

    Map<String, String> getHeaders();

    Map<String, String> getBodyParameters();

    String getMethod();

    void setMethod(String method);

    String getRequestUrl();

    void setRequestUrl(String requestUrl);

    void addHeader(String header, String value);

    void addCookie(HttpCookie cookie);

    void addBodyParameter(String parameter, String value);

    boolean isResource();
}
