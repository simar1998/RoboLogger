package com.vyo.robologger.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Helper class consists of static references of methods commonly used enough times to not deem their own class
 */
public class Helper {

    /**
     * Buffered Reader implementation for file reader, returns String object full of file contents. Location provided in argument.
     * @param filePath
     * @return
     */
    public static String fileRead(String filePath){
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            return sb.toString();
        } catch (IOException e) {
            System.err.println("File not found!  PATH:"+filePath);
            throw new RuntimeException(e);
        }
    }
}
