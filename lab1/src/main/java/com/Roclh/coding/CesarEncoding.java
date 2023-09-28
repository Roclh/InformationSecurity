package com.Roclh.coding;

import static com.Roclh.utils.EncodingUtils.ENGLISH_ALPHABET_SYMBOL_AMOUNT;
import static com.Roclh.utils.EncodingUtils.RUSSIAN_ALPHABET_SYMBOL_AMOUNT;
import static com.Roclh.utils.EncodingUtils.isEnglish;
import static com.Roclh.utils.EncodingUtils.isRussian;
import static com.Roclh.utils.EncodingUtils.isSpecialSymbol;

public class CesarEncoding implements Encoding {
    private final int offset;

    public CesarEncoding(int offset) {
        this.offset = offset;
    }

    @Override
    public String code(String text) {
        StringBuilder result = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (!isSpecialSymbol(character)) {
                char newCharacter;
                if (isRussian(character)) {
                    boolean isUpperCase = Character.isUpperCase(character);
                    int originalAlphabetPosition = (isUpperCase ? Character.toLowerCase(character) : character) - 'а';
                    int newAlphabetPosition = (originalAlphabetPosition + offset) % RUSSIAN_ALPHABET_SYMBOL_AMOUNT;
                    newCharacter = (char) ('а' + newAlphabetPosition);
                    result.append(isUpperCase ? Character.toUpperCase(newCharacter) : newCharacter);
                }
                if (isEnglish(character)) {
                    boolean isUpperCase = Character.isUpperCase(character);
                    int originalAlphabetPosition = (isUpperCase ? Character.toLowerCase(character) : character) - 'a';
                    int newAlphabetPosition = (originalAlphabetPosition + offset) % ENGLISH_ALPHABET_SYMBOL_AMOUNT;
                    newCharacter = (char) ('a' + newAlphabetPosition);
                    result.append(isUpperCase ? Character.toUpperCase(newCharacter) : newCharacter);
                }
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    @Override
    public String encode(String codedText) {
        StringBuilder result = new StringBuilder();
        for (char character : codedText.toCharArray()) {
            if (!isSpecialSymbol(character)) {
                char newCharacter;
                if (isRussian(character)) {
                    boolean isUpperCase = Character.isUpperCase(character);
                    int originalAlphabetPosition = (isUpperCase ? Character.toLowerCase(character) : character) - 'а';
                    int newAlphabetPosition = (originalAlphabetPosition + (RUSSIAN_ALPHABET_SYMBOL_AMOUNT - offset)) % RUSSIAN_ALPHABET_SYMBOL_AMOUNT;
                    newCharacter = (char) ('а' + newAlphabetPosition);
                    result.append(isUpperCase ? Character.toUpperCase(newCharacter) : newCharacter);
                }
                if (isEnglish(character)) {
                    boolean isUpperCase = Character.isUpperCase(character);
                    int originalAlphabetPosition = (isUpperCase ? Character.toLowerCase(character) : character) - 'a';
                    int newAlphabetPosition = (originalAlphabetPosition + (ENGLISH_ALPHABET_SYMBOL_AMOUNT - offset)) % ENGLISH_ALPHABET_SYMBOL_AMOUNT;
                    newCharacter = (char) ('a' + newAlphabetPosition);
                    result.append(isUpperCase ? Character.toUpperCase(newCharacter) : newCharacter);
                }
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }
}
