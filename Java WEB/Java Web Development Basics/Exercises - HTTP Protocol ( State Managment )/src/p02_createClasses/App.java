package p02_createClasses;

import p02_createClasses.http.impl.HttpRequestImpl;
import p02_createClasses.http.interfaces.HttpRequest;
import p02_createClasses.io.interfaces.Reader;
import p02_createClasses.io.interfaces.Writer;
import p02_createClasses.utils.Const;

import java.io.IOException;
import java.util.*;

public class App implements Runnable {

    private Reader reader;
    private Writer writer;

    public App(Reader reader, Writer writer) {
        this.reader = reader;
        this.writer = writer;
    }

    public void run() {
        String inputRequest = null;
        try {
            inputRequest = getRequest();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        HttpRequest httpRequest = null;
        if (inputRequest != null) {
            httpRequest = new HttpRequestImpl(inputRequest);
        }

        //Print request cookies
        Objects.requireNonNull(httpRequest).getCookies()
                .forEach(cookie ->
                        this.writer.writeLine(
                                String.format(Const.COOKIE_PATTERN, cookie.getKey(), cookie.getValue())
                        )
                );
    }

    private String getRequest() throws IOException {
        StringBuilder requestBuilder = new StringBuilder();

        String line;
        while ((line = this.reader.readLine()) != null) {
            if (line.length() == 0) {
                requestBuilder.append(System.lineSeparator());

                if (this.reader.ready()) {
                    if ((line = reader.readLine()) != null && line.length() > 0) {
                        requestBuilder.append(line);
                    }
                }
                break;
            }
            requestBuilder.append(line).append(System.lineSeparator());
        }
        return requestBuilder.toString();
    }
}
