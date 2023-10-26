package com.Roclh;

import com.Roclh.math.MathUtils;
import com.Roclh.utils.FileReader;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
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
        String Ctext = FileReader.readAllFile("C11.txt");
        List<Long> C = Arrays.stream(Ctext.split("\r\n")).map(Long::valueOf).toList();
        int pherma = MathUtils.pherma(N12, e12, true);
        AtomicInteger iterator = new AtomicInteger(0);
        String result = C.stream()
                .map(c -> {
                    long code = BigInteger.valueOf(c).pow(pherma).mod(BigInteger.valueOf(N12)).longValue();
                    String phrase = new String(ByteBuffer.allocate(Long.BYTES).putLong(code).array(), Charset.forName("windows-1251"));
                    System.out.printf("c = C[%d]^d mod N = %d^%d mod %d = %d => %s\n", iterator.getAndIncrement(), c, pherma, N12, code, phrase);
                    return phrase;
                }).collect(Collectors.joining());
        System.out.println("Resulting phrase: " + result);
    }
}
