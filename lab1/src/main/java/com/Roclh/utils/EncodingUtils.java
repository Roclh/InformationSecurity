package com.Roclh.utils;

public class EncodingUtils {
    private static final String russianSymbols = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    private static final String englishSymbols = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    private static final String specialSymbols = " .,/\\\r\n[]{}~!@#$%^&*()";

    public static final int RUSSIAN_ALPHABET_SYMBOL_AMOUNT = 33;

    public static final int ENGLISH_ALPHABET_SYMBOL_AMOUNT = 26;

    public static boolean isSpecialSymbol(char symbol){
        return specialSymbols.contains(String.valueOf(symbol));
    }
    public static boolean isRussian(char symbol){
        return russianSymbols.contains(String.valueOf(symbol));
    }

    public static boolean isEnglish(char symbol){
        return englishSymbols.contains(String.valueOf(symbol));
    }
}
