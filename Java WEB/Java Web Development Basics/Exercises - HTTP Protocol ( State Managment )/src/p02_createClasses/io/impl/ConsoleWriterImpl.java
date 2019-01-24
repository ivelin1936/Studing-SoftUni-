package p02_createClasses.io.impl;

import p02_createClasses.io.interfaces.Writer;

public class ConsoleWriterImpl implements Writer {

    public ConsoleWriterImpl() {

    }

    @Override
    public void write(String content) {
        System.out.print(content);
    }

    @Override
    public void writeLine(String line) {
        System.out.println(line);
    }
}
