package kr.superh81.Util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class Hash {
    /**
     * (Internal) Computes hash using given algorithm
     *
     * @param msg message to hash
     * @param alg hash algorithm
     * @return hash value
     */
    private static byte[] hashInternal(byte[] msg, String alg) {
        try {
            MessageDigest md = MessageDigest.getInstance(alg);
            md.update(msg);
            return md.digest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Computes SHA256
     *
     * @param msg message to sha256
     * @return SHA256 value
     */
    public static byte[] sha256(byte[] msg) {
        return hashInternal(msg, "SHA-256");
    }

    /**
     * Computes Base64-encoded SHA256
     *
     * @param msg     message to sha256
     * @param encoder Base64 encoder
     * @return Base64-encoded SHA256
     */
    public static String sha256(String msg, Base64.Encoder encoder) {
        try {
            byte[] sha = sha256(msg.getBytes(StandardCharsets.UTF_8));
            if (sha != null)
                return encoder.encodeToString(sha);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * (Internal) Computes mac using given algorithm
     *
     * @param msg message to HmacSHA256
     * @param key secret key for mac
     * @param alg mac algorithm
     * @return mac value
     */
    private static byte[] macInternal(byte[] msg, byte[] key, String alg) {
        try {
            byte[] secretKey = {0x00};
            if (key != null && key.length > 0) {
                secretKey = key;
            }

            Mac mac = Mac.getInstance(alg);
            mac.init(new SecretKeySpec(secretKey, alg));
            return mac.doFinal(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Computes HmacSHA256
     *
     * @param msg message to HmacSHA256
     * @param key secret key for mac
     * @return HmacSHA256 value
     */
    public static byte[] hmacSha256(byte[] msg, byte[] key) {
        return macInternal(msg, key, "HmacSHA256");
    }

    /**
     * Computes Base64-encoded HmacSHA256
     *
     * @param msg     message to HmacSHA256
     * @param key     secret key for mac
     * @param encoder Base64 encoder
     * @return Base64-encoded HmacSHA256
     */
    public static String hmacSha256(String msg, String key, Base64.Encoder encoder) {
        try {
            byte[] hmac = hmacSha256(msg.getBytes(StandardCharsets.UTF_8), key.getBytes(StandardCharsets.UTF_8));
            if (hmac != null)
                return encoder.encodeToString(hmac);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
