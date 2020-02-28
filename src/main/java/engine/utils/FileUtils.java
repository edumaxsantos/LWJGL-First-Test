package engine.utils;

import org.apache.commons.io.IOUtils;

import java.io.*;

public class FileUtils {

    public static String loadAsString(String path) {
        String result = "";
        try {
            result = String.join("\n", IOUtils.readLines(new FileInputStream(path), "UTF-8"));
        } catch (IOException e) {
            System.err.println("Couldn't find the file at " + path);
        }

        return result;
    }

}
