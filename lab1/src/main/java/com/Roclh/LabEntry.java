package com.Roclh;

import com.Roclh.coding.CesarWithKeyWordEncoding;
import com.Roclh.coding.Encoding;
import com.Roclh.utils.EncodingUtils;
import org.Roclh.common.files.FileReader;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class LabEntry {
    public static void main(String... args) throws IOException {
        String text = FileReader.readAllFile("FutureCoding.txt");
        Map<String, Integer> analizeMap = FileReader.countEntrances(text);
        Encoding cesarEncoding = new CesarWithKeyWordEncoding(EncodingUtils.buildAlphabet(analizeMap.keySet()),5, "Чтоsmth");
        String codedFile = cesarEncoding.code(text);
        String encodedFile = cesarEncoding.encode(codedFile);
        System.out.println("Начальный текст: ");
        System.out.println(text);
        System.out.println("Закодированный текст: ");
        System.out.println(codedFile);
        System.out.println("Раскодированный текст:");
        System.out.println(encodedFile);
        System.out.println("Частотный анализ: ");
        try (FileWriter fileWriter = new FileWriter("Output.txt")) {
            fileWriter.write(codedFile);
        }
        analizeMap.forEach(
                        (key, value) -> System.out.println("Символ: " + key + ", количество: " + value + ", частота: " +
                                ((double) value / analizeMap.values().stream().mapToDouble((i) -> i).sum()))
                );
    }
}
