package kr.superh81.Util;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import static org.junit.Assert.*;

@SuppressWarnings("FieldCanBeLocal")
public class RsaTest {
    private boolean testAssertNull = true;

    private String x509Cert = "-----BEGIN CERTIFICATE-----\n" +
            "MIICvDCCAaQCCQCjXGf3LM6wyjANBgkqhkiG9w0BAQsFADAgMQswCQYDVQQGEwJL\n" +
            "UjERMA8GA1UEAwwIc3VwZXJoODEwHhcNMjAwMTE5MTA0NTA3WhcNMzAwMTE2MTA0\n" +
            "NTA3WjAgMQswCQYDVQQGEwJLUjERMA8GA1UEAwwIc3VwZXJoODEwggEiMA0GCSqG\n" +
            "SIb3DQEBAQUAA4IBDwAwggEKAoIBAQDh6T0d7ooRi/E0q9s9Z7Ya6bkM+SiuprHO\n" +
            "jE7XmfhC4erA0HuJCiAH4ZZmh8TAXv+gvn7B1pB/eZOQao1PJlTHeWRo1wJ0GtCd\n" +
            "1v9R5cy8s8MJfJwOmzudqkwwbs9mvMKOAajjoK5L9ZYthOeyPjDZUBZiGaL3Hnwp\n" +
            "gJmP0HpMwYDKcdwRWY4LN0alYv/lJQR4I9nEmft7JwYgJ7Gjucwwb+nl8kCpbq68\n" +
            "dfRaRiTScM2k269F5fzix47GSP9dFOH4I6E+iOyuPLDGOZnS9PKy99jTLF8kj4TD\n" +
            "/VYtsYWg6q1UIz5W62GBDHGsf44Sp76SobK0SbEDZzfSASh3pgE5AgMBAAEwDQYJ\n" +
            "KoZIhvcNAQELBQADggEBAC/9tWt79l5nbVxmWezL3jKe6VyPQKGlsk1UNGtnXJAf\n" +
            "dVBD9s2/80vI53gYsF4fzAcCfkNg9RpqptlXsw5Vnn8WzBIkeNbJt3j9U2yrmpE2\n" +
            "6yqG+OynEmBFGaZHf4TCbN/vEdNAu5SCAHsnfCwT+X5erHCOw/dU5vMiML78anbH\n" +
            "cR0zQbS92J7HfArQQAdmD2wqZkQIuYwiAACQ8gboLQyaXCCN7vpsKSTbNSnerGan\n" +
            "2eB2z6Vk3Os5gqAaDZDuMvvYW5q4k12hyJmLNEZk0IXwIEh1Qz8DyhyN9q/13v9s\n" +
            "/1Uc72dkE/kccQNYFhM2UPwOggiC5qRsf5mp5TAobv4=\n" +
            "-----END CERTIFICATE-----\n";

    private String priKey = "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDh6T0d7ooRi/E0\n" +
            "q9s9Z7Ya6bkM+SiuprHOjE7XmfhC4erA0HuJCiAH4ZZmh8TAXv+gvn7B1pB/eZOQ\n" +
            "ao1PJlTHeWRo1wJ0GtCd1v9R5cy8s8MJfJwOmzudqkwwbs9mvMKOAajjoK5L9ZYt\n" +
            "hOeyPjDZUBZiGaL3HnwpgJmP0HpMwYDKcdwRWY4LN0alYv/lJQR4I9nEmft7JwYg\n" +
            "J7Gjucwwb+nl8kCpbq68dfRaRiTScM2k269F5fzix47GSP9dFOH4I6E+iOyuPLDG\n" +
            "OZnS9PKy99jTLF8kj4TD/VYtsYWg6q1UIz5W62GBDHGsf44Sp76SobK0SbEDZzfS\n" +
            "ASh3pgE5AgMBAAECggEAGC5mG1kYME6hb/jhS85ZIMJZy3i0ERXIvIoqOkvB1i7m\n" +
            "eOMYBhAuY4mTFsB7n/fLNwKNAOSdrPdvgyrUm19H/d4RkGNLYPAp2mtUAwktBBnd\n" +
            "y5sp/SpClFrRMvH5g067KIYm9M6TOZA9Ffw+7MZZZjl7W97QodECi1K3nTw8ixCL\n" +
            "4r7KujJFZlyVR41F+SniBTK2QWLFy35G7MVUEq03HIhMlbQto34+KdSBVFDocpXD\n" +
            "n6EFdNJY4hGpQYmf0UDAC2dDta1eEltScTDHdmiytZpXcCJlDtQGqa4WPF0pFL5j\n" +
            "p79wQRwdpeHDnf7Xr9Z+4Bjkl2VwibZaT/aVN0++gQKBgQDzrsrQsH22ooWeQrFJ\n" +
            "3Mpti1WJCG8D8EMqC8YPLWU6MnRwIcFB2RO1LFdblLe6Y+ygH1h6VGGKZNfmzZWI\n" +
            "RWYv9Gq1MOjgr3GSgTK/3xiOPjmdnveWUdnT9Qi6OcnTDIUbU/BRqhQIckAfUFiM\n" +
            "/qs9TUWuDnKy5U0YUbIG3ns/sQKBgQDtVHv3FqsHOn6B4VeNumjaCHpBQiIqXi7p\n" +
            "NyZyw/Dg5lHZ0QJYMqLOWjHSqA+lmxEbaBNQwcqZFiqCAuMTbxl2tRrK9LvmfYUh\n" +
            "rmk/8b0KV+AFwq6OK5qq8ZjCQRLjqqP1jJ8XIh3vQ5a7QBKBsR6ZoHR64BT15qw9\n" +
            "tMDyc/IECQKBgA/GykS2mH0BiqsTYE0K6WQsOC52CFH19TM8reb/pmUdLSKxXMS5\n" +
            "nCgHpeQ8/aQoogI/5UeOPP5rxvqfZsX60EHcjigK8ZjSYwT8Ll/zvrA+4RYOW7M0\n" +
            "u2iYnacI5MMwNVHunkl7PG7LjtE4L3N5bXa7kP5S2yQv6kzfxSnir5IRAoGBAI+T\n" +
            "7Osu64kT+jTY/v7fJ0/AkTWprMG0+OxeUQRdRfzJWftMVv7Eo3nzVvD6e3zUsyTB\n" +
            "ce4JT2gN4OmGlZnMPLt0FSFzrbljdodJ3KKyvIrlXsIdZTzdZFTGlIPwmlzhfw+u\n" +
            "YxC7syBs42Ok/402eiy2xcEQj520T7+E+rWzL1cBAoGBALMFSUpwrlF13b5++Tx3\n" +
            "TykBeAY3N72u/ItM5FxWS+uzFttROshUrVsBtX0ExphiTp9wVv84Na+V+ggbUk76\n" +
            "4LSpLGZou/zJMnL4OjKMJrcE+hcDdx8vRN74vb6B5iHB0lbVLz+Cce9uAdAVEDY1\n" +
            "eZiO8iDIjZNlv84ILOZzMwAx\n" +
            "-----END PRIVATE KEY-----\n";

    @Test
    public void getKeyAndSign() {
        RSAPublicKey rsaPublicKey = Rsa.getPublicKey(x509Cert);
        RSAPrivateKey rsaPrivateKey = Rsa.getPrivateKey(priKey);

        byte[] message = "It is the challenge.".getBytes(StandardCharsets.UTF_8);

        byte[] sign = Rsa.sign(message, rsaPrivateKey);
        assertTrue(Rsa.verify(message, sign, rsaPublicKey));

        if (testAssertNull) {
            assertNull(Rsa.getPublicKey(null));
            assertNull(Rsa.getPrivateKey(null));
            assertNull(Rsa.getPrivateKey(""));

            assertNull(Rsa.sign(null, rsaPrivateKey));
            assertFalse(Rsa.verify(null, null, rsaPublicKey));
        }
    }
}