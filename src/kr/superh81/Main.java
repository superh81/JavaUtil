package kr.superh81;

import kr.superh81.Util.ByteConverter;
import kr.superh81.Util.Hash;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Main {

    public static void main(String[] args) {
        testByteConverter();
        testHash();
    }

    private static void testByteConverter() {
//        byte[] dataB = {0x01, 0x0f, (byte) 0xdf, (byte) 0xcf, (byte) 0xeb};
        char[] dataC = {0x01, 0x0f, 0xdf, 0xcf, 0xeb};
        String b64 = "AQ/fz+s=";
        String b64Url = "AQ_fz-s";
        String hex = "q010fdfcfeb";

        System.out.println(ByteConverter.toBase64(dataC));
        System.out.println(ByteConverter.toBase64(dataC, true, false));
        System.out.println(ByteConverter.toBase64(dataC, true, true));
        System.out.println(ByteConverter.toBase64(dataC, false, true));
        System.out.println(ByteConverter.toBase64UrlSafe(dataC));

        System.out.println(ByteConverter.toHex(dataC));

        printByte(ByteConverter.base64ToByte(b64));
        printByte(ByteConverter.base64ToByte(b64Url));

        printByte(ByteConverter.hexToByte(hex));
    }

    private static void testHash() {
        // please check sha test vectors at https://tools.ietf.org/html/rfc4634#section-8.4
        // sha256("abc")
        // -> BA7816BF8F01CFEA414140DE5DAE2223B00361A396177A9CB410FF61F20015AD

        // please check hmac test vectors at https://tools.ietf.org/html/rfc4231#section-4
        // hmac-sha-256("what do ya want for nothing?", "Jefe")
        // -> 5bdcc146bf60754e6a042426089575c75a003f089d2739839dec58b964ec3843
        String hasMsg = "abc";
        String macMsg = "what do ya want for nothing?";
        String macKey = "Jefe";

        System.out.println(Hash.sha256(hasMsg, Base64.getEncoder()));
        printByte(Hash.sha256(hasMsg.getBytes(StandardCharsets.UTF_8)));
        System.out.println(Hash.hmacSha256(macMsg, macKey, Base64.getEncoder()));
        printByte(Hash.hmacSha256(macMsg.getBytes(StandardCharsets.UTF_8), macKey.getBytes(StandardCharsets.UTF_8)));
    }

    private static void printByte(byte[] data) {
        try {
            StringBuilder sb = new StringBuilder();
            for (byte b : data)
                sb.append(String.format(" %02x", b));
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
