package kr.superh81.Util;

import java.util.Base64;
import java.util.regex.Pattern;

public class ByteConverter {
    /**
     * Converts char array to byte array
     *
     * @param data char array
     * @return byte array
     */
    public static byte[] toByte(char[] data) {
        try {
            int len = data.length;
            byte[] ret = new byte[len];

            for (int i = 0; i < len; i++) {
                ret[i] = (byte) (data[i] & 0xff);
            }

            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Converts hex formatted String to byte array
     *
     * @param data hex formatted String
     * @return byte array
     */
    public static byte[] hexToByte(String data) {
        try {
            data = Pattern.compile("\\s").matcher(data).replaceAll("");
            int i, offset = 1, len = data.length();
            byte[] ret = new byte[(len + 1) / 2];

            if (len % 2 == 0) {
                ret[0] = (byte) (((hexCharToByteInternal(data.charAt(0)) << 4) + hexCharToByteInternal(data.charAt(1))) & 0xff);
                i = 2;
            } else {
                ret[0] = hexCharToByteInternal(data.charAt(0));
                i = 1;
            }

            for (; i < len; i += 2, offset++) {
                ret[offset] = (byte) (((hexCharToByteInternal(data.charAt(i)) << 4) + hexCharToByteInternal(data.charAt(i + 1))) & 0xff);
            }

            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * (Internal) Converts hex formatted char to byte value
     *
     * @param ch hex formatted char
     * @return byte value
     */
    private static byte hexCharToByteInternal(char ch) {
        if ('0' <= ch && ch <= '9')
            return (byte) (ch - 48);
        else if ('A' <= ch && ch <= 'Z')
            return (byte) (ch - 55); //(ch - 'A' + 10);
        else if ('a' <= ch && ch <= 'z')
            return (byte) (ch - 87); //(ch - 'a' + 10);
        else
            return 0;
    }

    /**
     * Decodes Base64 encoded String to byte array
     *
     * @param data Base64 encoded String
     * @return byte array
     */
    public static byte[] base64ToByte(String data) {
        try {
            return Base64.getDecoder().decode(data.replace("_", "/").replace("-", "+"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Converts byte array to hex formatted String
     *
     * @param data byte array
     * @return hex formatted String
     */
    public static String toHex(byte[] data) {
        try {
            StringBuilder sb = new StringBuilder();
            for (byte b : data) {
                sb.append(String.format("%02x", b & 0xff));
            }

            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Converts char array to hex formatted String
     *
     * @param data char array
     * @return hex formatted String
     */
    public static String toHex(char[] data) {
        return toHex(toByte(data));
    }

    /**
     * Encodes byte array to Base64 String
     *
     * @param data           byte array
     * @param urlSafe        flag for url safe Base64 encoding
     *                       If it is true, 'withoutPadding' will be true regardless of the input.
     * @param withoutPadding flag for no padding
     * @return Base64 encoded String
     */
    public static String toBase64(byte[] data, boolean urlSafe, boolean withoutPadding) {
        try {
            Base64.Encoder encoder;
            if (urlSafe)
                encoder = Base64.getUrlEncoder();
            else
                encoder = Base64.getEncoder();

            if (urlSafe || withoutPadding)
                encoder = encoder.withoutPadding();

            return encoder.encodeToString(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Encodes byte array to Base64 String
     *
     * @param data byte array
     * @return Base64 encoded String
     */
    public static String toBase64(byte[] data) {
        return toBase64(data, false, false);
    }

    /**
     * Encodes char array to Base64 String
     *
     * @param data           char array
     * @param urlSafe        flag for url safe Base64 encoding
     * @param withoutPadding flag for no padding
     * @return Base64 encoded String
     */
    public static String toBase64(char[] data, boolean urlSafe, boolean withoutPadding) {
        return toBase64(toByte(data), urlSafe, withoutPadding);
    }

    /**
     * Encodes char array to Base64 String
     *
     * @param data char array
     * @return Base64 encoded String
     */
    public static String toBase64(char[] data) {
        return toBase64(toByte(data));
    }

    /**
     * Encodes byte array to url-safe-Base64 String
     *
     * @param data byte array
     * @return url-safe-Base64 encoded String
     */
    public static String toBase64UrlSafe(byte[] data) {
        try {
            return Base64.getUrlEncoder().withoutPadding().encodeToString(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Encodes char array to url-safe-Base64 String
     *
     * @param data char array
     * @return url-safe-Base64 encoded String
     */
    public static String toBase64UrlSafe(char[] data) {
        return toBase64UrlSafe(toByte(data));
    }
}
