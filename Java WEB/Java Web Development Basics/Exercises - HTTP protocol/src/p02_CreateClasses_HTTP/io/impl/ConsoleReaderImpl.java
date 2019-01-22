package p02_CreateClasses_HTTP.io.impl;

import p02_CreateClasses_HTTP.io.interfaces.Reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReaderImpl implements Reader {

    private BufferedReader reader;

    public ConsoleReaderImpl() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public String readLine() throws IOException {
        return this.reader.readLine();
    }

    @Override
    public boolean ready() throws IOException {
        return this.reader.ready();
    }
}
