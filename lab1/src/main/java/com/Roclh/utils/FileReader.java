package com.Roclh.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static com.Roclh.utils.EncodingUtils.isSpecialSymbol;

public class FileReader {

    public static String readAllFile(String filepath) {
        File file = new File(filepath);
        if (!file.exists()) {
            createNewFile(file.getAbsolutePath());
        }
        StringBuilder result = new StringBuilder();
        if (canRead(file)) {
            try (FileInputStream stream = new FileInputStream(file)) {
                result.append(new String(stream.readAllBytes(), StandardCharsets.UTF_8));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }

    public static void createNewFile(String filename) {
        try {
            File file = new File(filename);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Failed to create file " + filename);
            e.printStackTrace();
        }
    }

    public static Map<String, Integer> countEntrances(String text) {
        Map<String, Integer> entrances = new HashMap<>();
        for (char c : text.toCharArray()) {
            if(isSpecialSymbol(c)) continue;
            String key = String.valueOf(c);
            entrances.merge(key, 1, Integer::sum);
        }
        return entrances;
    }

    private static boolean canRead(File file) {
        return file != null && file.exists() && file.canRead();
    }
}
