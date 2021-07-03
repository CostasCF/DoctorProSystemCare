import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Main
{

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt); //random salt generated
        KeySpec spec = new PBEKeySpec("password".toCharArray(), salt, 65536, 512);
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] hash = f.generateSecret(spec).getEncoded();

      // for (byte i:  hash) {
     //     System.out.print(String.valueOf(i)+ "\n");
   //   }

        PasswordAuthentication _passwordauth = new PasswordAuthentication();
        String hash1 = _passwordauth.hash("test".toCharArray());    //returns a hash to save it into the database.
        boolean match = _passwordauth.authenticate("test".toCharArray(),hash1); //returns true if password matches the hash

        System.out.print(hash1 + "\n");
        System.out.println("Password match: " + match);

        Base64.Encoder enc = Base64.getEncoder();
        System.out.printf("salt: %s%n", enc.encodeToString(salt));
        System.out.printf("hash: %s%n", enc.encodeToString(hash));
    }
}

// https://www.baeldung.com/java-password-hashing
// https://stackoverflow.com/questions/2860943/how-can-i-hash-a-password-in-java
// https://security.stackexchange.com/questions/195563/why-is-sha-256-not-good-for-passwords