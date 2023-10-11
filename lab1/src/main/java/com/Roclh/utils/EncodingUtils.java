package com.Roclh.utils;

import java.util.HashMap;
import java.util.Map;

public class EncodingUtils {
    public static final String RUSSIAN_ALPHABET_SYMBOLS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public static final String ENGLISH_ALPHABET_SYMBOLS = "abcdefghijklmnopqrstuvwxyz";

    public static final String SPECIAL_SYMBOLS = " .,/\\\r\n[]{}~!@#$%^&*()";

    public static final int RUSSIAN_ALPHABET_SYMBOL_AMOUNT = 33;

    public static final int ENGLISH_ALPHABET_SYMBOL_AMOUNT = 26;

    public static boolean isSpecialSymbol(char symbol) {
        return SPECIAL_SYMBOLS.contains(String.valueOf(symbol));
    }

    public static boolean isRussian(char symbol) {
        String s = String.valueOf(symbol);
        return RUSSIAN_ALPHABET_SYMBOLS.contains(Character.isUpperCase(symbol) ? s.toLowerCase() : s);
    }

    public static boolean isEnglish(char symbol) {
        String s = String.valueOf(symbol);
        return ENGLISH_ALPHABET_SYMBOLS.contains(Character.isUpperCase(symbol) ? s.toLowerCase() : s);
    }

    public static boolean containsIdenticalSymbols(String text) {
        char[] arr = text.toCharArray();
        Map<Character, Integer> hashMap = new HashMap<>();
        for (char c : arr) {
            if (hashMap.containsKey(c)) {
                return true;
            } else {
                hashMap.put(c, 1);
            }
        }
        return false;
    }

    public enum Language {
        RUSSIAN("Russian"),
        ENGLISH("English");

        private final String text;

        Language(String text) {
            this.text = text;
        }

        public String text() {
            return this.text;
        }

    }
}
