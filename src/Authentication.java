import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
/*
public class Authentication {
    static setPin(int pin) {
        BigInteger bigInt = BigInteger.valueOf(pin);
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        md.update(salt);
        byte[] hashedPassword = md.digest(bigInt.toByteArray());
        return new Bank.BankAccount.PIN(salt, hashedPassword);
    }
}


 */