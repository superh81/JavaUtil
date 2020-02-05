package kr.superh81.Util;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@SuppressWarnings("WeakerAccess")
public class Rsa {
    /**
     * Gets RSA private key from PEM-encoded PKCS#8 private key
     *
     * @param pemKey PEM-encoded PKCS#8 private key
     * @return RSA private key
     */
    public static RSAPrivateKey getPrivateKey(String pemKey) {
        try {
            String beginPkcs8Plain = "-----BEGIN PRIVATE KEY-----";
            String endPkcs8Plain = "-----END PRIVATE KEY-----";

            if (pemKey.startsWith(beginPkcs8Plain)) {
                // plain PKCS8
                pemKey = pemKey.replace(beginPkcs8Plain, "").replace(endPkcs8Plain, "").replaceAll("\\s", "");
            } else {
                throw new Exception("Unsupported private key format.");
            }

            KeyFactory kf = KeyFactory.getInstance("RSA");
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(pemKey));
            return (RSAPrivateKey) kf.generatePrivate(spec);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets certificate object from PEM-encoded X.509 certificate
     *
     * @param pemCert PEM-encoded X.509 certificate
     * @return certificate object
     */
    public static Certificate getCertificate(String pemCert) {
        try {
            CertificateFactory fact = CertificateFactory.getInstance("X.509");
            ByteArrayInputStream bis = new ByteArrayInputStream(pemCert.getBytes(StandardCharsets.UTF_8));
            return fact.generateCertificate(bis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * gets RSA public key from PEM-encoded X.509 certificate
     *
     * @param pemCert PEM-encoded X.509 certificate
     * @return RSA public key
     */
    public static RSAPublicKey getPublicKey(String pemCert) {
        X509Certificate cer = (X509Certificate) getCertificate(pemCert);
        if (cer != null)
            return (RSAPublicKey) cer.getPublicKey();
        return null;
    }

    /**
     * Signs message using SHA256withRSA
     *
     * @param msg message to sign
     * @param key RSA private key
     * @return signature
     */
    public static byte[] sign(byte[] msg, RSAPrivateKey key) {
        try {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initSign(key);
            signature.update(msg);
            return signature.sign();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Verifies signature of message using SHA256withRSA
     *
     * @param msg  message to verify
     * @param sign signature to verify
     * @param key  RSA public key
     * @return true if the signature was verified, false if not.
     */
    public static boolean verify(byte[] msg, byte[] sign, RSAPublicKey key) {
        try {
            Signature signature = Signature.getInstance("SHA256withRSA");
            signature.initVerify(key);
            signature.update(msg);
            return signature.verify(sign);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
