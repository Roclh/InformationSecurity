package com.Roclh.coding;

import com.Roclh.utils.EncodingUtils;

import static com.Roclh.utils.EncodingUtils.ENGLISH_ALPHABET_SYMBOLS;
import static com.Roclh.utils.EncodingUtils.RUSSIAN_ALPHABET_SYMBOLS;
import static com.Roclh.utils.EncodingUtils.isEnglish;
import static com.Roclh.utils.EncodingUtils.isRussian;
import static com.Roclh.utils.EncodingUtils.isSpecialSymbol;
import static org.junit.Assert.assertFalse;

public class CesarWithKeyWordEncoding implements Encoding {

    private final int offset;
    private final String russianAlphabet;
    private final String englishAlphabet;

    public CesarWithKeyWordEncoding(int offset, String keyword) {
        assertFalse("Keyword contains identical symbols!", EncodingUtils.containsIdenticalSymbols(keyword.toLowerCase()));
        this.offset = offset;
        String russianKeyword = EncodingUtils.extractSumbols(keyword.toLowerCase(), EncodingUtils.Language.RUSSIAN);
        String englishKeyword = EncodingUtils.extractSumbols(keyword.toLowerCase(), EncodingUtils.Language.ENGLISH);
        this.russianAlphabet = insertKeyword(RUSSIAN_ALPHABET_SYMBOLS, russianKeyword, offset);
        this.englishAlphabet = insertKeyword(ENGLISH_ALPHABET_SYMBOLS, englishKeyword, offset);
    }

    @Override
    public String code(String text) {
        StringBuilder result = new StringBuilder();
        for (char character : text.toCharArray()) {
            if (!isSpecialSymbol(character)) {
                char newCharacter;
                boolean isUpperCase = Character.isUpperCase(character);
                if (isRussian(character)) {
                    int originalAlphabetPosition = russianAlphabet.indexOf(isUpperCase ? Character.toLowerCase(character) : character);
                    int newAlphabetPosition = (originalAlphabetPosition + offset) % russianAlphabet.length();
                    newCharacter = russianAlphabet.charAt(newAlphabetPosition);
                    result.append(isUpperCase ? Character.toUpperCase(newCharacter) : newCharacter);
                }
                if(isEnglish(character)){
                    int originalAlphabetPosition = englishAlphabet.indexOf(isUpperCase ? Character.toLowerCase(character) : character);
                    int newAlphabetPosition = (originalAlphabetPosition + offset) % englishAlphabet.length();
                    newCharacter = englishAlphabet.charAt(newAlphabetPosition);
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
                boolean isUpperCase = Character.isUpperCase(character);
                if(isRussian(character)){
                    int originalAlphabetPosition = russianAlphabet.indexOf(isUpperCase ? Character.toLowerCase(character) : character);
                    int newAlphabetPosition = (originalAlphabetPosition + (russianAlphabet.length() - offset)) % russianAlphabet.length();
                    newCharacter = russianAlphabet.charAt(newAlphabetPosition);
                    result.append(isUpperCase ? Character.toUpperCase(newCharacter) : newCharacter);
                }
                if(isEnglish(character)){
                    int originalAlphabetPosition = englishAlphabet.indexOf(isUpperCase ? Character.toLowerCase(character) : character);
                    int newAlphabetPosition = (originalAlphabetPosition + (englishAlphabet.length() - offset)) % englishAlphabet.length();
                    newCharacter = englishAlphabet.charAt(newAlphabetPosition);
                    result.append(isUpperCase ? Character.toUpperCase(newCharacter) : newCharacter);
                }
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }


    private String insertKeyword(String alphabet, String keyword, int offset) {
        assertFalse("Keyword is bigger than alphabet!", alphabet.length() < keyword.length());
        StringBuilder alphabetWithoutKeywordSymbols = new StringBuilder();
        for (char k : alphabet.toCharArray()) {
            if (!keyword.contains(String.valueOf(k))) {
                alphabetWithoutKeywordSymbols.append(k);
            }
        }
        int leftover = Math.abs(alphabet.length() - (alphabetWithoutKeywordSymbols.length() + keyword.length()));
        String afterKeyWord = alphabetWithoutKeywordSymbols.substring(leftover, alphabetWithoutKeywordSymbols.length() - offset);
        String beforeKeyWord = alphabetWithoutKeywordSymbols.substring(alphabetWithoutKeywordSymbols.length() - offset);
        return beforeKeyWord + keyword + afterKeyWord;
    }
}
