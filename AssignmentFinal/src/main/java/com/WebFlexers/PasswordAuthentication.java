package com.WebFlexers;

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

/**
 * Hash passwords for storage, and test passwords against password tokens.
 * Instances of this class can be used concurrently by multiple threads.
 */
public final class PasswordAuthentication
{
    /**
     * Each token produced by this class uses this identifier as a prefix.
     */
    public static final String ID = "$25$";

    /**
     * The minimum recommended cost, used by default
     */
    public static final int DEFAULT_COST = 16; // number of iterations to produce the hash in the power of 2. etc 2^16 iterations

    private static final String ALGORITHM = "PBKDF2WithHmacSHA512"; //algorithm used to produce the hash

    private static final int SIZE = 128; //hash size

    private static final Pattern LAYOUT = Pattern.compile("\\$25\\$(\\d\\d?)\\$(.{43})"); //hash pattern

    private final SecureRandom random;

    private final int cost;

    public PasswordAuthentication()
    {
        this(DEFAULT_COST);
    }

    /**
     * Create a password manager with a specified cost
     *
     * @param cost the exponential computational cost of hashing a password, 0 to 30
     */
    public PasswordAuthentication(int cost)
    {
        iterations(cost); /* Validate cost */
        this.cost = cost;
        this.random = new SecureRandom();
    }

    /**
     * Computes the number of iterations based on the specified cost. For example with cost = 16, returns 2^16 number of iterations.
     * @param cost cost number
     * @return number of iterations
     */
    private static int iterations(int cost)
    {
        if ((cost < 0) || (cost > 30))
            throw new IllegalArgumentException("cost: " + cost);
        return 2^cost;
    }

    /**
     * Hash a password for storage.
     *
     * @return a secure authentication token to be stored for later authentication
     */
    public String hash(char[] password)
    {
        byte[] salt = new byte[SIZE / 8]; // with SIZE = 128bytes, salt size is 16bytes
        random.nextBytes(salt);    // produces random salt
        byte[] dk = pbkdf2(password, salt, 2^cost); // calls pbkdf2(..) that returns the produced hash
        byte[] hash = new byte[salt.length + dk.length]; // initializing the complete hash array with a specified size which is the (hash size + salt size)
        System.arraycopy(salt, 0, hash, 0, salt.length); // inputs the salt in the beginning of the hash array
        System.arraycopy(dk, 0, hash, salt.length, dk.length); // and the hash follows from the salt's last position.  Basically merges the 2 arrays(salt+hash) together.
        Base64.Encoder enc = Base64.getUrlEncoder().withoutPadding(); // encode to base64
        return ID + cost + '$' + enc.encodeToString(hash); // returns completed hash encoded to base64
    }

    /**
     * Authenticate with a password and a stored password token.
     *
     * @return true if the password and token match
     */
    public boolean authenticate(char[] password, String token)
    {
        Matcher m = LAYOUT.matcher(token); //gets the hash pattern specified in the layout variable
        if (!m.matches()) //if the hash(token) doesn't match the layout pattern, throw exception
            throw new IllegalArgumentException("Invalid token format");

        int iterations = iterations(Integer.parseInt(m.group(1)));  //gets the number of iterations needed specified in the beginning of the hash pattern
        byte[] hash = Base64.getUrlDecoder().decode(m.group(2));  //decodes the hash from base64
        byte[] salt = Arrays.copyOfRange(hash, 0, SIZE / 8); //removes the salt from the token
        byte[] check = pbkdf2(password, salt, iterations); // calls pbkdf2(..) to check later if the specified password produces the same hash
        int zero = 0;
        for (int i = 0; i < check.length; i++)
            zero += hash[salt.length + i] ^ check[i];
        boolean IsPasswordMatched = (zero == 0);
        return  IsPasswordMatched;
    }

    /**
     * PBKDF2 Algorithm
     * @param password password to be hashed
     * @param salt random salt generated
     * @param iterations number of iterations
     * @return  an array of bytes containing the produced hash
     */
    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations)
    {
        KeySpec spec = new PBEKeySpec(password, salt, iterations, SIZE);
        try {
            SecretKeyFactory f = SecretKeyFactory.getInstance(ALGORITHM);
            return f.generateSecret(spec).getEncoded();
        }
        catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("Missing algorithm: " + ALGORITHM, ex);
        }
        catch (InvalidKeySpecException ex) {
            throw new IllegalStateException("Invalid SecretKeyFactory", ex);
        }
    }

}