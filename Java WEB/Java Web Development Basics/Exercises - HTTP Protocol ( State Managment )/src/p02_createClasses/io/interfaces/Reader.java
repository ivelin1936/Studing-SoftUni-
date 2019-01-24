package p02_createClasses.io.interfaces;

import java.io.IOException;

public interface Reader {

    String readLine() throws IOException;

    boolean ready() throws IOException;
}
