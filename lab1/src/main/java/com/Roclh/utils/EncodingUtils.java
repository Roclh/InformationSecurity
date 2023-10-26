package com.Roclh.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class EncodingUtils {
    public static final String RUSSIAN_ALPHABET_SYMBOLS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public static final String ENGLISH_ALPHABET_SYMBOLS = "abcdefghijklmnopqrstuvwxyz";

    public static final String SPECIAL_SYMBOLS = " .,/\\\r\n[]{}~!@#$%^&*;?:()—-\"'`«";

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

    public static String extractSumbols(String text, Language language){
        return text.chars().mapToObj(String::valueOf).filter(language.alphabet::contains).collect(Collector.of(
                StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append,
                StringBuilder::toString));
    }

    public enum Language {
        RUSSIAN("Russian", RUSSIAN_ALPHABET_SYMBOLS),
        ENGLISH("English", ENGLISH_ALPHABET_SYMBOLS);

        private final String text;
        private final String alphabet;

        Language(String text, String alphabet) {
            this.text = text;
            this.alphabet = alphabet;
        }

        public String text() {
            return this.text;
        }

        public String getAlphabet(){
            return this.alphabet;
        }

    }

    public static String buildAlphabet(Set<String> symbols){
        String extraSymbols = symbols.stream()
                .filter(s -> !RUSSIAN_ALPHABET_SYMBOLS.contains(s) && !RUSSIAN_ALPHABET_SYMBOLS.toUpperCase().contains(s))
                .filter(s -> !ENGLISH_ALPHABET_SYMBOLS.contains(s) && !ENGLISH_ALPHABET_SYMBOLS.toUpperCase().contains(s))
                .filter(s -> !SPECIAL_SYMBOLS.contains(s))
                .collect(Collectors.joining());
        return RUSSIAN_ALPHABET_SYMBOLS + ENGLISH_ALPHABET_SYMBOLS + SPECIAL_SYMBOLS + extraSymbols;
    }
}
