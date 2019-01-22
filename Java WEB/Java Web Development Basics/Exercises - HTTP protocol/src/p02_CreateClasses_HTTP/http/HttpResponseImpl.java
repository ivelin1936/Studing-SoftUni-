package p02_CreateClasses_HTTP.http;

import p02_CreateClasses_HTTP.utils.Const;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class HttpResponseImpl implements HttpResponse {

    private Map<String, String> headers;
    private byte[] content;
    private int statusCode;

    public HttpResponseImpl() {
        this.setContent(new byte[0]);
        this.headers = new HashMap<>();
    }

    @Override
    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(this.headers);
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public byte[] getContent() {
        return this.content;
    }

    @Override
    public byte[] getBytes() {
        byte[] headersBytes = this.getHeadersBytes();
        byte[] bodyBytes = this.getContent();

        byte[] fullResponse = new byte[headersBytes.length + bodyBytes.length];

        System.arraycopy(headersBytes, 0, fullResponse, 0, headersBytes.length);
        System.arraycopy(bodyBytes, 0, fullResponse, headersBytes.length, bodyBytes.length);

        return fullResponse;
    }

    @Override
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }

    private byte[] getHeadersBytes() {
        String responseFirstLine = String.format(Const.FIRST_LINE_PATTERN,
                Const.DEFAULT_HTTP_RES_VERSION,
                this.getStatusPhrase(),
                System.lineSeparator()
        );

        StringBuilder headers = new StringBuilder(responseFirstLine);
        /**Append headers*/
        for (Map.Entry<String, String> entry : this.headers.entrySet()) {
            headers.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append(System.lineSeparator());
        }

        /**If request don't have Date in headers, response will return Date header with current date*/
        if (!this.headers.containsKey(Const.PROCESS_HEADER_DATE)) {
            SimpleDateFormat sdf = new SimpleDateFormat(Const.DATE_FORMAT_PATTERN);
            String currentDate = sdf.format(new Date());
            headers.append(String.format(Const.DATE_HEADER_PATTERN, currentDate))
                    .append(System.lineSeparator());
        }

        /**Append empty line after the headers*/
        headers.append(System.lineSeparator());

        return headers.toString().getBytes();
    }

    /** Get Status Phrase by Status Code */
    //TODO -> make it with Enumeration!!!
    private String getStatusPhrase() {
        String status = null;
        switch (this.getStatusCode()) {
            case Const.STATUS_CODE_200:
                status = Const.STATUS_OK;
                break;
            case Const.STATUS_CODE_201:
                status = Const.STATUS_CREATED;
                break;
            case Const.STATUS_CODE_400:
                status = Const.STATUS_BAD_REQUEST;
                break;
            case Const.STATUS_CODE_303:
                status = Const.STATUS_SEE_OTHER;
                break;
            case Const.STATUS_CODE_401:
                status = Const.STATUS_UNAUTHORIZED;
                break;
            case Const.STATUS_CODE_403:
                status = Const.STATUS_FORBIDDEN;
                break;
            case Const.STATUS_CODE_404:
                status = Const.STATUS_NOT_FOUND;
                break;
            case Const.STATUS_CODE_500:
                status = Const.STATUS_INTERNAL_SERVER_ERROR;
                break;
        }
        return status;
    }
}
