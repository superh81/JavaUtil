package kr.superh81.Util;

import org.junit.Before;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static org.junit.Assert.*;

@SuppressWarnings("FieldCanBeLocal")
public class HashTest {
    private boolean testAssertNull = true;

    // https://csrc.nist.gov/CSRC/media/Projects/Cryptographic-Standards-and-Guidelines/documents/examples/SHA256.pdf
    // https://csrc.nist.gov/CSRC/media/Projects/Cryptographic-Standards-and-Guidelines/documents/examples/SHA512.pdf
    private String shaInput = "abc";
    private byte[] shaInputByte;
    private String sha256Hex = "BA7816BF 8F01CFEA\n" +
            "414140DE 5DAE2223 B00361A3 96177A9C B410FF61 F20015AD";
    private byte[] sha256Byte;
    private String sha256Base64;
    private String sha512Hex = "DDAF35A1 93617ABA CC417349 AE204131\n" +
            "12E6FA4E 89A97EA2 0A9EEEE6 4B55D39A 2192992A 274FC1A8\n" +
            "36BA3C23 A3FEEBBD 454D4423 643CE80E 2A9AC94F A54CA49F ";
    private byte[] sha512Byte;
    private String sha512Base64;

    // https://tools.ietf.org/html/rfc4231#section-4.3
    private String hmacInput = "what do ya want for nothing?";
    private byte[] hmacInputByte;
    private String hmacKey = "Jefe";
    private byte[] hmacKeyByte;
    private String hmac256Hex = "5bdcc146bf60754e6a042426089575c7\n" +
            "                  5a003f089d2739839dec58b964ec3843";
    private byte[] hmac256Byte;
    private String hmac256Base64;
    private String hmac512Hex = "164b7a7bfcf819e2e395fbe73b56e0a3\n" +
            "                  87bd64222e831fd610270cd7ea250554\n" +
            "                  9758bf75c05a994a6d034f65f8f0e6fd\n" +
            "                  caeab1a34d4a6b4b636e070a38bce737";
    private byte[] hmac512Byte;
    private String hmac512Base64;

    @Before
    public void setUp() {
        shaInputByte = shaInput.getBytes(StandardCharsets.UTF_8);
        sha256Byte = ByteConverter.hexToByte(sha256Hex);
        sha256Base64 = Base64.getEncoder().encodeToString(sha256Byte);
        sha512Byte = ByteConverter.hexToByte(sha512Hex);
        sha512Base64 = Base64.getEncoder().encodeToString(sha512Byte);

        hmacInputByte = hmacInput.getBytes(StandardCharsets.UTF_8);
        hmacKeyByte = hmacKey.getBytes(StandardCharsets.UTF_8);
        hmac256Byte = ByteConverter.hexToByte(hmac256Hex);
        hmac256Base64 = Base64.getEncoder().encodeToString(hmac256Byte);
        hmac512Byte = ByteConverter.hexToByte(hmac512Hex);
        hmac512Base64 = Base64.getEncoder().encodeToString(hmac512Byte);
    }

    @Test
    public void sha256() {
        assertArrayEquals(sha256Byte, Hash.sha256(shaInputByte));
        assertEquals(sha256Base64, Hash.sha256(shaInput, Base64.getEncoder()));

        if (testAssertNull) {
            assertNull(Hash.sha256(null));
            assertNull(Hash.sha256(null, null));
        }
    }

    @Test
    public void sha512() {
        assertArrayEquals(sha512Byte, Hash.sha512(shaInputByte));
        assertEquals(sha512Base64, Hash.sha512(shaInput, Base64.getEncoder()));

        if (testAssertNull) {
            assertNull(Hash.sha512(null, null));
        }
    }

    @Test
    public void hmacSha256() {
        assertArrayEquals(hmac256Byte, Hash.hmacSha256(hmacInputByte, hmacKeyByte));
        assertEquals(hmac256Base64, Hash.hmacSha256(hmacInput, hmacKey, Base64.getEncoder()));

        if (testAssertNull) {
            assertNull(Hash.hmacSha256(null, null));
            assertNull(Hash.hmacSha256(hmacInputByte, null));
            assertNull(Hash.hmacSha256(hmacInput, hmacKey, null));
        }
    }

    @Test
    public void hmacSha512() {
        assertArrayEquals(hmac512Byte, Hash.hmacSha512(hmacInputByte, hmacKeyByte));
        assertEquals(hmac512Base64, Hash.hmacSha512(hmacInput, hmacKey, Base64.getEncoder()));

        if (testAssertNull) {
            assertNull(Hash.hmacSha512(hmacInput, hmacKey, null));
        }
    }
}
