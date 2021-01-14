package com.unisoft.core.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CoreUtilTest {

    private static final byte[] BYTES = "Hello world!".getBytes(StandardCharsets.UTF_8);
    private static final byte[] UTF_8_BOM = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};
    private static final byte[] UTF_16BE_BOM = {(byte) 0xFE, (byte) 0xFF};
    private static final byte[] UTF_16LE_BOM = {(byte) 0xFF, (byte) 0xFE};
    private static final byte[] UTF_32BE_BOM = {(byte) 0x00, (byte) 0x00, (byte) 0xFE, (byte) 0xFF};
    private static final byte[] UTF_32LE_BOM = {(byte) 0xFF, (byte) 0xFE, (byte) 0x00, (byte) 0x00};

    private static Stream<Arguments> bomAwareToStringSupplier() {
        return Stream.of(
                Arguments.arguments(null, null, null),
                Arguments.arguments(BYTES, null, new String(BYTES, StandardCharsets.UTF_8)),
                Arguments.arguments(BYTES, "charset=UTF-16BE", new String(BYTES, StandardCharsets.UTF_16BE)),
                Arguments.arguments(BYTES, "charset=invalid", new String(BYTES, StandardCharsets.UTF_8)),
                Arguments.arguments(addBom(UTF_8_BOM), null, new String(BYTES, StandardCharsets.UTF_8)),
                Arguments.arguments(addBom(UTF_16BE_BOM), null, new String(BYTES, StandardCharsets.UTF_16BE)),
                Arguments.arguments(addBom(UTF_16LE_BOM), null, new String(BYTES, StandardCharsets.UTF_16LE)),
                Arguments.arguments(addBom(UTF_32BE_BOM), null, new String(BYTES, Charset.forName("UTF-32BE"))),
                Arguments.arguments(addBom(UTF_32LE_BOM), null, new String(BYTES, Charset.forName("UTF-32LE"))),
                Arguments.arguments(addBom(UTF_8_BOM), "charset=UTF-8", new String(BYTES, StandardCharsets.UTF_8)),
                Arguments.arguments(addBom(UTF_8_BOM), "charset=UTF-16BE", new String(BYTES, StandardCharsets.UTF_8))
        );
    }

    private static byte[] addBom(byte[] arr1) {
        byte[] mergedArray = new byte[arr1.length + BYTES.length];

        System.arraycopy(arr1, 0, mergedArray, 0, arr1.length);
        System.arraycopy(BYTES, 0, mergedArray, arr1.length, BYTES.length);

        return mergedArray;
    }

    @Test
    void isNonNullOrEmpty() {
        assertTrue(CoreUtil.isNullOrEmpty(""));
        String charSequence = null;
        assertTrue(CoreUtil.isNullOrEmpty(charSequence));

        Map<Object, Object> map = null;
        assertTrue(CoreUtil.isNullOrEmpty(map));
    }

    @Test
    void requireNonNullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> CoreUtil.requireNonNullOrEmpty("", "should not be empty"));
        assertThrows(NullPointerException.class, () -> CoreUtil.requireNonNullOrEmpty(null, "should not be null"));
    }

    @Test
    void disposeOfCharArray() {
        final char[] originalChars = "randomChar".toCharArray();
        final char[] copyRandomChars = "randomChar".toCharArray();
        CoreUtil.dispose(originalChars);
        assertNotEquals(originalChars, copyRandomChars);
        for (int i = 0; i < originalChars.length; i++) {
            if (originalChars[i] == copyRandomChars[i]) {
                fail();
            }
        }
    }


    @ParameterizedTest
    @MethodSource("bomAwareToStringSupplier")
    void bomAwareToString(byte[] bytes, String contentType, String expected) {
        assertEquals(expected, CoreUtil.bomAwareToString(bytes, contentType));
    }

    @Test
    void copy() {
        final byte[] originalBytes = "randomChar".getBytes();
        final byte[] copyBytes = CoreUtil.clone(originalBytes);
        final byte[] copyNull = CoreUtil.clone(null);
        assertNull(copyNull);
        for (int i = 0; i < originalBytes.length; i++) {
            assertEquals(originalBytes[i], copyBytes[i]);
        }
    }
}