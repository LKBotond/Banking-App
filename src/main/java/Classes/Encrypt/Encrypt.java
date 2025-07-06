package Classes.Encrypt;

import java.util.Base64;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.GCMParameterSpec;
import java.security.SecureRandom;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class Encrypt {
    // Argon2 params
    private int iterations = 4;
    int memory = 131072;
    int parallelism = 2;

    // AES-GCM params
    int bytes = 12;
    int bit_Length = 128;

    public String HashWithArgon2(String password) {
        Argon2 engine = Argon2Factory.create();
        char[] pass_Array = password.toCharArray();
        try {
            return engine.hash(iterations, memory, parallelism, pass_Array);
        } finally {
            engine.wipeArray(pass_Array);
        }
    }

    public boolean verifyPass(String hash, String password) {
        Argon2 engine = Argon2Factory.create();
        char[] pass_Array = password.toCharArray();
        try {
            return engine.verify(hash, pass_Array);
        } finally {
            engine.wipeArray(pass_Array);
        }
    }

    public ArrayList<String> encryptWithAESGCM(String data, String key) {
        byte[] iv = new byte[bytes];
        new SecureRandom().nextBytes(iv);
        ArrayList<String> encrypted_And_IV = new ArrayList<>();
        try {
            SecretKeySpec secret_Key = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec gcm_Spec = new GCMParameterSpec(bit_Length, iv);

            cipher.init(Cipher.ENCRYPT_MODE, secret_Key, gcm_Spec);

            byte[] encrypted = cipher.doFinal(data.getBytes());
            encrypted_And_IV.add(Base64.getEncoder().encodeToString(iv));
            encrypted_And_IV.add(Base64.getEncoder().encodeToString(encrypted));

        } catch (Exception e) {
            System.out.println(e);
        }
        return encrypted_And_IV;

    }

    public String decryptGCM(ArrayList<String> encrypted_And_IV, String key) {
        String decrypted_String = "FAILIURE";
        try {
            byte[] iv = Base64.getDecoder().decode(encrypted_And_IV.get(0));
            byte[] encrypted = Base64.getDecoder().decode(encrypted_And_IV.get(1));

            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            GCMParameterSpec gcmSpec = new GCMParameterSpec(bit_Length, iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmSpec);

            byte[] decrypted = cipher.doFinal(encrypted);
            decrypted_String = new String(decrypted);
        } catch (Exception e) {
            System.out.println(e);
        }
        return decrypted_String;
    }
}
