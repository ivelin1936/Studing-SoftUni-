package p02_CreateClasses_HTTP.io.impl;

import p02_CreateClasses_HTTP.io.interfaces.Writer;

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
