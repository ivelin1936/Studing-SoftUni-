package com.softuni.realestate.util.htmlReader;

import java.io.IOException;

public interface HtmlFileReader {

    String readFile(String htmlFilePath) throws IOException;
}
