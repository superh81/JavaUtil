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
            if (msg == null)
                throw new NullPointerException();

            MessageDigest md = MessageDigest.getInstance(alg);
            md.update(msg);
            return md.digest();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Computes SHA-256
     *
     * @param msg message to SHA-256
     * @return SHA-256 value
     */
    public static byte[] sha256(byte[] msg) {
        return hashInternal(msg, "SHA-256");
    }

    /**
     * Computes Base64-encoded SHA-256
     *
     * @param msg     message to SHA-256
     * @param encoder Base64 encoder
     * @return Base64-encoded SHA-256
     */
    public static String sha256(String msg, Base64.Encoder encoder) {
        try {
            byte[] sha = sha256(msg.getBytes(StandardCharsets.UTF_8));
            return encoder.encodeToString(sha);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Computes SHA-512
     *
     * @param msg message to SHA-512
     * @return SHA-512 value
     */
    public static byte[] sha512(byte[] msg) {
        return hashInternal(msg, "SHA-512");
    }

    /**
     * Computes Base64-encoded SHA-512
     *
     * @param msg     message to SHA-512
     * @param encoder Base64 encoder
     * @return Base64-encoded SHA-512
     */
    public static String sha512(String msg, Base64.Encoder encoder) {
        try {
            byte[] sha = sha512(msg.getBytes(StandardCharsets.UTF_8));
            return encoder.encodeToString(sha);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * (Internal) Computes mac using given algorithm
     *
     * @param msg message to mac
     * @param key secret key for mac
     * @param alg mac algorithm
     * @return mac value
     */
    private static byte[] macInternal(byte[] msg, byte[] key, String alg) {
        try {
            if (key == null)
                throw new NullPointerException();

            Mac mac = Mac.getInstance(alg);
            mac.init(new SecretKeySpec(key, alg));
            return mac.doFinal(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Computes Hmac-SHA256
     *
     * @param msg message to Hmac-SHA256
     * @param key secret key for mac
     * @return Hmac-SHA256 value
     */
    public static byte[] hmacSha256(byte[] msg, byte[] key) {
        return macInternal(msg, key, "HmacSHA256");
    }

    /**
     * Computes Base64-encoded Hmac-SHA256
     *
     * @param msg     message to Hmac-SHA256
     * @param key     secret key for mac
     * @param encoder Base64 encoder
     * @return Base64-encoded Hmac-SHA256
     */
    public static String hmacSha256(String msg, String key, Base64.Encoder encoder) {
        try {
            byte[] hmac = hmacSha256(msg.getBytes(StandardCharsets.UTF_8), key.getBytes(StandardCharsets.UTF_8));
            return encoder.encodeToString(hmac);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Computes Hmac-SHA512
     *
     * @param msg message to Hmac-SHA512
     * @param key secret key for mac
     * @return Hmac-SHA512 value
     */
    public static byte[] hmacSha512(byte[] msg, byte[] key) {
        return macInternal(msg, key, "HmacSHA512");
    }

    /**
     * Computes Base64-encoded Hmac-SHA512
     *
     * @param msg     message to Hmac-SHA512
     * @param key     secret key for mac
     * @param encoder Base64 encoder
     * @return Base64-encoded Hmac-SHA512
     */
    public static String hmacSha512(String msg, String key, Base64.Encoder encoder) {
        try {
            byte[] hmac = hmacSha512(msg.getBytes(StandardCharsets.UTF_8), key.getBytes(StandardCharsets.UTF_8));
            return encoder.encodeToString(hmac);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
