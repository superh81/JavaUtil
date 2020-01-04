package kr.superh81;

import kr.superh81.Util.ByteConverter;

public class Main {

    public static void main(String[] args) {
        testByteConverter();
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
