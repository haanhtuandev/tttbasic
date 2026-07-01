package vgu.pe2026.ttt.server_singlethread_multiuser;

import java.nio.charset.StandardCharsets;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class HashObject {
    private final String mySecretString = "super-secret-key-123";

    public String calculateHMAC(String data, SecretKey key) throws Exception {
        key = new SecretKeySpec(mySecretString.getBytes(StandardCharsets.UTF_8),"HmacSHA256");
        Mac sha256HMAC = Mac.getInstance("HmacSHA256");
        
        // 2. Initialize it with your secret key
        sha256HMAC.init(key);
        
        // 3. Compute the raw hash bytes
        byte[] rawHmacBytes = sha256HMAC.doFinal(data.getBytes(StandardCharsets.UTF_8));
        
        // 4. Convert raw bytes to Hex string format
        StringBuilder hexString = new StringBuilder();
        for (byte b : rawHmacBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        
        return hexString.toString();
}
}
