package org.Roclh.common.encoding;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public class Encode {


    public static String cp1251(int code) {
        return new String(ByteBuffer.allocate(Integer.BYTES).putInt(code).array(), Charset.forName("windows-1251"));
    }

    public static String cp1251(int code, ByteOrder byteOrder) {
        return new String(ByteBuffer.allocate(Integer.BYTES).order(byteOrder)
                .putInt(code).array(), Charset.forName("cp1251"));
    }
}
