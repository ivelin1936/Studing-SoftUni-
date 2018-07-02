package IO.impl;

import IO.interfaces.Writer;

public class ConsoleWriter implements Writer {

    public ConsoleWriter() {
    }

    @Override
    public void writeLine(String line) {
        System.out.println(line);
    }
}
