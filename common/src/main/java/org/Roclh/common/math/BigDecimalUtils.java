package org.Roclh.common.math;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtils {

    public static BigDecimal intPower(BigDecimal x, long exponent, int scale) {
        // If the exponent is negative, compute 1/(x^-exponent).
        if (exponent < 0) {
            return BigDecimal.valueOf(1).divide(
                    intPower(x, -exponent, scale), scale,
                    RoundingMode.HALF_EVEN);
        }

        BigDecimal power = BigDecimal.valueOf(1);

        // Loop to compute value^exponent.
        while (exponent > 0) {

            // Is the rightmost bit a 1?
            if ((exponent & 1) == 1) {
                power = power.multiply(x).setScale(scale,
                        RoundingMode.HALF_EVEN);
            }

            // Square x and shift exponent 1 bit to the right.
            x = x.multiply(x).setScale(scale, RoundingMode.HALF_EVEN);
            exponent >>= 1;

            Thread.yield();
        }

        return power;
    }

    public static BigDecimal intRoot(BigDecimal x, long index, int scale){
        // Check that x >= 0.
        if (x.signum() < 0) {
            throw new IllegalArgumentException("x < 0");
        }

        int sp1 = scale + 1;
        BigDecimal n = x;
        BigDecimal i = BigDecimal.valueOf(index);
        BigDecimal im1 = BigDecimal.valueOf(index - 1);
        BigDecimal tolerance = BigDecimal.valueOf(5).movePointLeft(sp1);
        BigDecimal xPrev;

        // The initial approximation is x/index.
        x = x.divide(i, scale, RoundingMode.HALF_EVEN);

        // Loop until the approximations converge
        // (two successive approximations are equal after rounding).
        do {
            // x^(index-1)
            BigDecimal xToIm1 = intPower(x, index - 1, sp1);

            // x^index
            BigDecimal xToI = x.multiply(xToIm1).setScale(sp1,
                    RoundingMode.HALF_EVEN);

            // n + (index-1)*(x^index)
            BigDecimal numerator = n.add(im1.multiply(xToI)).setScale(sp1,
                    RoundingMode.HALF_EVEN);

            // (index*(x^(index-1))
            BigDecimal denominator = i.multiply(xToIm1).setScale(sp1,
                    RoundingMode.HALF_EVEN);

            // x = (n + (index-1)*(x^index)) / (index*(x^(index-1)))
            xPrev = x;
            x = numerator.divide(denominator, sp1, RoundingMode.DOWN);

            Thread.yield();
        } while (x.subtract(xPrev).abs().compareTo(tolerance) > 0);

        return x;
    }
}
