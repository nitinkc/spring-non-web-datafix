package com.nonWeb.datafix.fileDataFix;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

@Service
public class DataWriteIntoFile {

    public static void doSomethingParallely(String singleString, Path path) {
        Charset characterSet = Charset.defaultCharset();
        try {
            //System.out.println("Writing into file the word :: " + singleString);
            //Files.write(path, Collections.singleton(singleString), characterSet, StandardOpenOption.APPEND);
            Files.write(path, Collections.singleton(singleString), characterSet);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
