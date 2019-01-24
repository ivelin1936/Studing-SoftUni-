package p02_createClasses;

import p02_createClasses.io.impl.ConsoleReaderImpl;
import p02_createClasses.io.impl.ConsoleWriterImpl;
import p02_createClasses.io.interfaces.Reader;
import p02_createClasses.io.interfaces.Writer;

public class Main {
    public static void main(String[] args) {

        Reader reader = new ConsoleReaderImpl();
        Writer writer = new ConsoleWriterImpl();

        App httpApp = new App(reader, writer);
        httpApp.run();
    }
}
