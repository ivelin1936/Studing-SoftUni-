package fdmc.util;

import java.io.*;

public class HtmlFileReaderImpl implements HtmlFileReader {

    public HtmlFileReaderImpl() {}

    @Override
    public String readFile(String htmlFilePath) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File(htmlFilePath))));

        StringBuilder htmlFileContent = new StringBuilder();
        String fileLine;
        while ((fileLine = reader.readLine()) != null) {
            htmlFileContent.append(fileLine)
                    .append(System.lineSeparator());
        }

        return htmlFileContent.toString().trim();
    }
}
