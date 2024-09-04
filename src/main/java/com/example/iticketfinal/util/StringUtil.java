package com.example.iticketfinal.util;

import org.springframework.stereotype.Component;

@Component
public class StringUtil {

    public String removeSpaces(String input) {
        if (input == null) {
            return null;
        }
        return input.replaceAll("\\s+", "");
    }
    public String[] divideFilename(String filename) {
        if (filename == null || !filename.contains(".")) {
            return null;
        }

        int lastDotIndex = filename.lastIndexOf(".");

        if (lastDotIndex == -1 || lastDotIndex == filename.length() - 1) {
            return null;
        }

        String baseName = filename.substring(0, lastDotIndex);
        String extension = filename.substring(lastDotIndex + 1);

        return new String[]{baseName, extension};
    }

}
