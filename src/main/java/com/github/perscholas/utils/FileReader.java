package com.github.perscholas.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * @author leonhunter
 * @created 02/12/2020 - 6:26 PM
 * used to convert files contents to String
 */
public class FileReader {

    private final String filename;

    public FileReader(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        try {
            byte[] readAllBytes = java.nio.file.Files.readAllBytes(Paths.get( filename ));
            return new String( readAllBytes );
        } catch (IOException e) {
            throw new Error(e);
        }
    }
}

