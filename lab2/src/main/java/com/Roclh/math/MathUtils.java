package com.Roclh.math;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MathUtils {

    public static Pair<Long, Long> closestSquare(long N, boolean log){
        long i = 0;
        System.out.println("n = [sqrt(N)] + 1 = " + (long)Math.sqrt(N) + 1);
        long n = (long)Math.sqrt(N) + 1;
        while (true) {
            i += 1;
            long t = n + i;
            if (log) {
                System.out.printf("t[%d] = n + %d = %d\n", i, i, t);
            }
            long w = (long)Math.pow(t, 2) - N;
            if (log) {
                System.out.printf("w[%d] = t[%d] ^ 2 - N = %d - %d = %d\n", i, i, t * t, N, w);
            }
            double sqrt_w = Math.sqrt(w);
            if (isRound(sqrt_w)) {
                long sqrt_wi = (long) sqrt_w;
                System.out.printf("sqrt[w] = %d\n", sqrt_wi);
                return new Pair<>(sqrt_wi, t);
            } else {
                if (log) {
                    System.out.printf("sqrt(w) = %f -> error\n", sqrt_w);
                }
            }
        }
    }

    public static long reversedExponent(long t, long sqrt_w, long e, boolean log) {
        long p = t + sqrt_w;
        long q = t - sqrt_w;
        long phi = (p - 1) * (q - 1);
        long d = BigInteger.valueOf(e).modInverse(BigInteger.valueOf(phi)).longValue();
        if(log) {
            System.out.printf("p = t + sqrt(w) = %d + %d = %d\n", t, sqrt_w, p);
            System.out.printf("q = t - sqrt(w) = %d + %d = %d\n", t, sqrt_w, q);
            System.out.printf("Phi(N) = (p - 1) * (q - 1) = (%d) * (%d) = %d\n", p - 1, q - 1, phi);
            System.out.printf("d = e^(-1) mod Phi(N) = %d^(-1) mod %d = %d\n", e, phi, d);
        }
        return d;
    }

    public static long pherma(long N, long e, boolean log) {
        Pair<Long, Long> closestSquare = closestSquare(N, log);
        return reversedExponent(closestSquare.right(), closestSquare.left(), e, log);
    }

    private static boolean isRound(double number) {
        return number % 1 == 0;
    }
}
