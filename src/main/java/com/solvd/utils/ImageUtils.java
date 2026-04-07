package com.solvd.utils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public final class ImageUtils {

    private ImageUtils() {
    }

    public static String toBase64(String resourcePath) {
        try {
            InputStream inputStream = Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream(resourcePath);

            if (inputStream != null) {
                try (inputStream) {
                    return Base64.getEncoder().encodeToString(inputStream.readAllBytes());
                }
            }

            Path testResourcePath = Path.of("src", "test", "resources", resourcePath);
            if (Files.exists(testResourcePath)) {
                return Base64.getEncoder().encodeToString(Files.readAllBytes(testResourcePath));
            }

            Path mainResourcePath = Path.of("src", "main", "resources", resourcePath);
            if (Files.exists(mainResourcePath)) {
                return Base64.getEncoder().encodeToString(Files.readAllBytes(mainResourcePath));
            }

            throw new RuntimeException("Image not found: " + resourcePath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load image: " + resourcePath, e);
        }
    }
}