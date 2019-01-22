package p02_CreateClasses_HTTP.io.interfaces;

import java.io.IOException;

public interface Reader {

    String readLine() throws IOException;

    boolean ready() throws IOException;
}
