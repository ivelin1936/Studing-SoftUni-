package p06_animals.IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public final class ConsoleIO {

    public ConsoleIO() {
    }

    public final String readLine() {
        String line = null;

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return line;
    }

    public final void writeLine(String line) {
        System.out.println(line);
    }
}
