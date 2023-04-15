package com.example.demo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestFileUtil {
    public static byte[] fileToByteArray(String fileName) {
        Path path = Path.of(fileName);
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(path);
            return bytes;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String fileToString(String fileName) {
        Path path = Path.of(fileName);
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(path);
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
