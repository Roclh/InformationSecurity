package com.Roclh;

import com.Roclh.math.MathUtils;
import com.Roclh.utils.FileReader;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class LabEntry {

    private final static long N11 = 7533841359567L;
    private final static long e11 = 3063167L;

    private final static long N12 =74701165267919L;
    private final static long e12 = 3145553L;


    public static void main(String... args) {
        String Ctext = FileReader.readAllFile("C12.txt");
        List<Long> C = Arrays.stream(Ctext.split("\r\n")).map(Long::valueOf).toList();
        long pherma = MathUtils.pherma(N12, e12, true);
        AtomicInteger iterator = new AtomicInteger(0);
        String result = C.stream()
                .map(c -> {
                    int code = BigInteger.valueOf(c).modPow(BigInteger.valueOf(pherma), BigInteger.valueOf(N12)).intValue();
                    String phrase = new String(ByteBuffer.allocate(Integer.BYTES).putInt(code).array(), Charset.forName("windows-1251"));
                    System.out.printf("c = C[%d]^d mod N = %d^%d mod %d = %d => %s\n", iterator.getAndIncrement(), c, pherma, N12, code, phrase);
                    return phrase;
                }).collect(Collectors.joining());
        System.out.println("Resulting phrase: " + result);
    }
}
