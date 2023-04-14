package com.example.demo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestFileUtil {
    public static byte[] readFileAsString(String fileName) {
        Path path = Path.of(fileName);
        byte[] bytes;
        try {
            bytes = Files.readAllBytes(path);
            System.out.println(new String(bytes, StandardCharsets.UTF_8));
            return bytes;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
