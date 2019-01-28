package fdmc.util;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface HtmlFileReader {

    String readFile(String htmlFilePath) throws IOException;
}
