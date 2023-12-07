package org.Roclh;

import org.Roclh.common.encoding.Encode;
import org.Roclh.common.files.FileReader;
import org.Roclh.common.math.BigDecimalUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.List;

public class LabEntry {

    private static final long N1 = 457829717113L;
    private static final long N2 = 461639371789L;
    private static final long N3 = 463811451073L;


    public static void main(String[] args) {
        List<Long> c1 = Arrays.stream(FileReader.readAllFile("lab5c11").split("\r\n")).map(Long::valueOf).toList();
        List<Long> c2 = Arrays.stream(FileReader.readAllFile("lab5c11_2").split("\r\n")).map(Long::valueOf).toList();
        List<Long> c3 = Arrays.stream(FileReader.readAllFile("lab5c11_3").split("\r\n")).map(Long::valueOf).toList();

        BigInteger bigN1 = BigInteger.valueOf(N1);
        BigInteger bigN2 = BigInteger.valueOf(N2);
        BigInteger bigN3 = BigInteger.valueOf(N3);

        BigInteger m3 = bigN1.multiply(bigN2);
        BigInteger M0 = m3.multiply(bigN3);
        BigInteger m1 = bigN2.multiply(bigN3);
        BigInteger m2 = bigN1.multiply(bigN3);
        BigInteger n1 = m1.modPow(BigInteger.ONE.negate(), bigN1);
        BigInteger n2 = m2.modPow(BigInteger.ONE.negate(), bigN2);
        BigInteger n3 = m3.modPow(BigInteger.ONE.negate(), bigN3);

        System.out.printf("M0 = N1 * N2 * N3 = %d * %d * %d = %s\n", N1, N2, N3, M0);
        System.out.printf("m1 = N2 * N3 = %d * %d = %d\n", N2, N3, m1.longValue());
        System.out.printf("m2 = N1 * N3 = %d * %d = %d\n", N1, N3, m2.longValue());
        System.out.printf("m3 = N1 * N2 = %d * %d = %d\n", N1, N2, m3.longValue());
        System.out.printf("n1 = m1^(-1) mod N1 = %d^(-1) mod %d = %d\n", m1.longValue(), N1, n1.longValue());
        System.out.printf("n2 = m2^(-1) mod N2 = %d^(-1) mod %d = %d\n", m2.longValue(), N2, n2.longValue());
        System.out.printf("n3 = m3^(-1) mod N3 = %d^(-1) mod %d = %d\n", m3.longValue(), N3, n3.longValue());

        StringBuilder message = new StringBuilder();
        for (int i = 0; i < c1.size(); i++) {
            BigInteger bigC1 = BigInteger.valueOf(c1.get(i)).multiply(n1).multiply(m1);
            BigInteger bigC2 = BigInteger.valueOf(c2.get(i)).multiply(n2).multiply(m2);
            BigInteger bigC3 = BigInteger.valueOf(c3.get(i)).multiply(n3).multiply(m3);
            BigInteger s = bigC1.add(bigC2).add(bigC3);
            BigInteger sModM0 = s.mod(M0);
            BigInteger M = BigDecimalUtils.intRoot(new BigDecimal(sModM0), 3, 1).toBigInteger();
            String mes = Encode.cp1251(M.intValue(), ByteOrder.BIG_ENDIAN);
            message.append(mes);
            System.out.printf("S[%d] = c1[%d]*n1*m1 + c2[%d]*n2*m2 + c3[%d]*n3*m3 = %s\n", i, i, i, i, s);
            System.out.printf("SmodM0[%d] = S[%d] mod M0 = %s\n", i, i, sModM0);
            System.out.printf("M = SmodM0[%d]^(1/3) = %d => text(M) = %s\n", i, M, mes);
        }
        System.out.println(message);
    }
}