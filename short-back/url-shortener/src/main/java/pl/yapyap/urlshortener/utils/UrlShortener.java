package pl.yapyap.urlshortener.utils;

import lombok.experimental.UtilityClass;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@UtilityClass
public class UrlShortener {
    private static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public String hashUrlToShort(String longUrl) {
        byte[] md5Bytes = md5(longUrl);

        BigInteger number = new BigInteger(1, md5Bytes); // 1 = unsigned

        String base62 = toBase62(number);

        return base62.substring(0, 7);
    }

    private byte[] md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            return md.digest(input.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 not available", e);
        }
    }

    private String toBase62(BigInteger number) {
        StringBuilder sb = new StringBuilder();
        BigInteger base = BigInteger.valueOf(62);
        while (number.compareTo(BigInteger.ZERO) > 0) {
            BigInteger[] divmod = number.divideAndRemainder(base); // number, remainder
            int ind = divmod[1].intValue();
            sb.append(BASE62.charAt(ind));
            number = divmod[0];
        }
        return sb.toString();
    }
}
