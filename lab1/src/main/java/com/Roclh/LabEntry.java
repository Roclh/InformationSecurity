package com.Roclh;

import com.Roclh.coding.CesarWithKeyWordEncoding;
import com.Roclh.coding.Encoding;
import com.Roclh.utils.FileReader;

import java.util.Map;

public class LabEntry {
    public static void main(String... args) {
        Encoding cesarEncoding = new CesarWithKeyWordEncoding(5, "Чтоsmth");
        String text = FileReader.readAllFile("FutureCoding.txt");
        String codedFile = cesarEncoding.code(text);
        String encodedFile = cesarEncoding.encode(codedFile);
        System.out.println("Начальный текст: ");
        System.out.println(text);
        System.out.println("Закодированный текст: ");
        System.out.println(codedFile);
        System.out.println("Раскодированный текст:");
        System.out.println(encodedFile);
        System.out.println("Частотный анализ: ");
        Map<String, Integer> analizeMap = FileReader.countEntrances(text);
        analizeMap.forEach(
                        (key, value) -> System.out.println("Символ: " + key + ", количество: " + value + ", частота: " +
                                ((double) value / analizeMap.values().stream().mapToDouble((i) -> i).sum()))
                );
    }
}
