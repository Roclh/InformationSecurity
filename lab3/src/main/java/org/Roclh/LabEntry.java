package org.Roclh;

import com.Roclh.utils.FileReader;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

public class LabEntry {
    private static final long N = 301916099393L;
    private static final long e = 301319L;

    public static void main(String[] args) {
        String Ctext = FileReader.readAllFile("lab3C11.txt");
        List<Long> C = Arrays.stream(Ctext.split("\r\n")).map(Long::valueOf).toList();
        C.forEach(c -> {
            BigInteger num = BigInteger.valueOf(c).modPow(BigInteger.valueOf(e), BigInteger.valueOf(N));
            BigInteger res = BigInteger.ZERO;
            while (!num.equals(BigInteger.valueOf(c))) {
                res = num;
                num = num.modPow(BigInteger.valueOf(e), BigInteger.valueOf(N));
            }
            String result = new String(ByteBuffer.allocate(Integer.BYTES).order(ByteOrder.BIG_ENDIAN)
                    .putInt(res.intValue()).array(), Charset.forName("cp1251"));
            System.out.print(result);
        });
    }

//    public static void testEncoding() {
//        String Ctext = FileReader.readAllFile("lab3C11.txt");
//        List<Long> C = Arrays.stream(Ctext.split("\r\n")).map(Long::valueOf).toList();
//        SortedMap<String, Charset> encodings = Charset.availableCharsets();
//        encodings.forEach((key, charset) -> {
//            String pass = C.stream().map(c -> {
//                BigInteger num = BigInteger.valueOf(c).modPow(BigInteger.valueOf(e), BigInteger.valueOf(N));
//                BigInteger res = BigInteger.ZERO;
//                while (!res.equals(BigInteger.valueOf(c))) {
//                    res = num;
//                    num = num.modPow(BigInteger.valueOf(e), BigInteger.valueOf(N));
//                }
//                return new String(ByteBuffer.allocate(Integer.BYTES).order(ByteOrder.BIG_ENDIAN).putInt(res.intValue()).array(), charset);
//            }).collect(Collectors.joining());
//            System.out.println(key + ": " + pass);
//        });
//    }
}