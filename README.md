# JavaUtil
Java util code for personal study

## ByteConverter
- It converts hex formatted `String` and `byte[]` to each other.
- It encodes `byte[]` to Base64 encoded `String`.
- It decodes Base64 encoded `String` to `byte[]`.

## Hash
- It computes SHA-256 and SHA-512.
- It computes HMAC-SHA256 and HMAC-SHA512.
- You can add SHA and HMAC methods simply using internal method.

## RSA
- It extracts RSA public key from PEM-encoded X.509 certificate.
- It extracts RSA private key from PEM-encoded PKCS #8 format private key.
- It signs message and verifies signature using SHA256 with RSA.
