package org.Roclh;

import org.Roclh.common.encoding.Encode;
import org.Roclh.common.files.FileReader;
import org.Roclh.common.objects.Triple;
import org.Roclh.math.MathUtils;

import java.math.BigInteger;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.List;

public class LabEntry {
    private final static int variant = 11;
    private final static long N = 287726313019L;
    private final static long e1 = 632699;
    private final static long e2 = 418997;

    public static void main(String[] args) {
        Triple<BigInteger, BigInteger, BigInteger> ars = MathUtils.euclidAlgorithm(BigInteger.valueOf(e1), BigInteger.valueOf(e2));
        List<Long> c1 = Arrays.stream(FileReader.readAllFile("lab4c11").split("\r\n")).map(Long::valueOf).toList();
        List<Long> c2 = Arrays.stream(FileReader.readAllFile("lab4c11_2").split("\r\n")).map(Long::valueOf).toList();
        StringBuilder message = new StringBuilder();
        BigInteger bigN = BigInteger.valueOf(N);
        for (int i = 0; i < c1.size(); i++) {
            BigInteger c1r = BigInteger.valueOf(c1.get(i)).modPow(ars.getSecond(), bigN);
            BigInteger c2s = BigInteger.valueOf(c2.get(i)).modPow(ars.getThird(), bigN);
            BigInteger m = (c1r.multiply(c2s).mod(bigN));
            String mes = Encode.cp1251(m.intValue(), ByteOrder.BIG_ENDIAN);
            message.append(mes);
            System.out.printf("(C1[%d]^r) mod N = %d\n", i, c1r.longValue());
            System.out.printf("(C2[%d]^s) mod N = %d\n", i, c2s.longValue());
            System.out.printf("m[%d] = (%d * %d) mod %d = %d => %d = %s\n", i, c1r.longValue(), c2s.longValue(), bigN.longValue(), m.longValue(), m.longValue(), mes);
        }

        System.out.println(message);


    }
}
