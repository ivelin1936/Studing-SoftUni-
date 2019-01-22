package p02_CreateClasses_HTTP;

import p02_CreateClasses_HTTP.io.impl.ConsoleReaderImpl;
import p02_CreateClasses_HTTP.io.impl.ConsoleWriterImpl;
import p02_CreateClasses_HTTP.io.interfaces.Reader;
import p02_CreateClasses_HTTP.io.interfaces.Writer;

public class Main {
    public static void main(String[] args) {

        Reader reader = new ConsoleReaderImpl();
        Writer writer = new ConsoleWriterImpl();

        App httpApp = new App(reader, writer);
        httpApp.run();
    }
}
