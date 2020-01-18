package kr.superh81.Util;

import org.junit.Test;

import static org.junit.Assert.*;

@SuppressWarnings("FieldCanBeLocal")
public class ByteConverterTest {
    private boolean testAssertNull = true;

    private byte[] dataB = {0x01, 0x0f, (byte) 0xdf, (byte) 0xcf, (byte) 0xeb};
    private char[] dataC = {0x01, 0x0f, 0xdf, 0xcf, 0xeb};
    private String b64 = "AQ/fz+s=";
    private String b64NoPad = "AQ/fz+s";
    private String b64Url = "AQ_fz-s";
    private String hex = "010fdfcfeb";
    private String hex2 = "1.fdfcfEB";

    @Test
    public void toByte() {
        assertArrayEquals(dataB, ByteConverter.toByte(dataC));

        if( testAssertNull ) {
            assertNull(ByteConverter.toByte(null));
        }
    }

    @Test
    public void hexToByte() {
        assertArrayEquals(dataB, ByteConverter.hexToByte(hex));
        assertArrayEquals(dataB, ByteConverter.hexToByte(hex2));

        if( testAssertNull ) {
            assertNull(ByteConverter.hexToByte(null));
        }

    }

    @Test
    public void base64ToByte() {
        assertArrayEquals(dataB, ByteConverter.base64ToByte(b64));
        assertArrayEquals(dataB, ByteConverter.base64ToByte(b64NoPad));
        assertArrayEquals(dataB, ByteConverter.base64ToByte(b64Url));

        if( testAssertNull ) {
            assertNull(ByteConverter.base64ToByte(null));
        }
    }

    @Test
    public void toHex() {
        assertEquals(hex, ByteConverter.toHex(dataC));

        if( testAssertNull ) {
            assertNull(ByteConverter.toHex((byte[]) null));
        }
    }

    @Test
    public void toBase64() {
        assertEquals(b64, ByteConverter.toBase64(dataC));
        assertEquals(b64, ByteConverter.toBase64(dataC, false, false));
        assertEquals(b64NoPad, ByteConverter.toBase64(dataC, false, true));
        assertEquals(b64Url, ByteConverter.toBase64(dataC, true, false));
        assertEquals(b64Url, ByteConverter.toBase64(dataC, true, true));

        if( testAssertNull ) {
            assertNull(ByteConverter.toBase64((byte[]) null, false, false));
        }
    }

    @Test
    public void toBase64UrlSafe() {
        assertEquals(b64Url, ByteConverter.toBase64UrlSafe(dataC));

        if( testAssertNull ) {
            assertNull(ByteConverter.toBase64UrlSafe((byte[]) null));
        }
    }
}
