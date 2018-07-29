import core.Engine;
import interfaces.Appender;
import interfaces.File;
import interfaces.Layout;
import interfaces.Logger;
import io.impl.ConsoleReader;
import io.impl.ConsoleWriter;
import io.interfaces.Reader;
import io.interfaces.Writer;
import models.appenders.ConsoleAppender;
import models.appenders.FileAppender;
import models.enums.ReportLevel;
import models.files.LogFile;
import models.layouts.SimpleLayout;
import models.loggers.MessageLogger;

public class Main {
    public static void main(String[] args) {
        Reader reader = new ConsoleReader();
        Writer writer = new ConsoleWriter();

        Engine engine = new Engine(reader, writer);
        engine.run();
    }
}
