package org.Roclh.math;

import org.Roclh.common.objects.Triple;

import java.math.BigInteger;

public class MathUtils {

    public static Triple<BigInteger, BigInteger, BigInteger> euclidAlgorithm(BigInteger n1, BigInteger n2) {
        Triple<BigInteger, BigInteger, BigInteger> res;
        if (n1.equals(BigInteger.ZERO)) {
            return new Triple<>(n2, BigInteger.ZERO, BigInteger.ONE);
        } else {
            res = euclidAlgorithm(n2.mod(n1), n1);
        }
        return new Triple<>(res.getFirst(), res.getThird().subtract(n2.divide(n1).multiply(res.getSecond())), res.getSecond());
    }

}
