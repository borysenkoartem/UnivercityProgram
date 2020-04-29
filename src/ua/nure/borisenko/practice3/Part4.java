package ua.nure.borisenko.practice3;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

public class Part4 {
	private static final int NUM = 16;
	

	public static String hash(String input, String algorithm) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance(algorithm);
		digest.reset();
		digest.update(input.getBytes());
		byte[] hash = digest.digest();
		BigInteger bigInt = new BigInteger(1, hash);
		String md5Hex = bigInt.toString(NUM);
		
		return md5Hex.toUpperCase(Locale.UK);
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(hash("asdf", "MD5"));
		System.out.println(hash("asdf", "SHA-256"));
	}
}
