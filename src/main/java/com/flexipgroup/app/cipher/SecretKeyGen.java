package com.flexipgroup.app.cipher;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import com.flexipgroup.app.log.FileLogger;

public class SecretKeyGen {
	
	private static SecretKey generateSecretKey1 ()
	{
		final SecureRandom prng = new SecureRandom();
	    final byte[] aes128KeyData  = new byte[128 / Byte.SIZE];
	    prng.nextBytes(aes128KeyData);    
	    final SecretKey aesKey = new SecretKeySpec(aes128KeyData, "AES");
	    return aesKey;
	}

 public static SecretKey generateSecretKey() {
  KeyGenerator keyGen = null;
  try {
   /*
    * Get KeyGenerator object that generates secret keys for the
    * specified algorithm.
    */
   keyGen = KeyGenerator.getInstance("AES");
  } catch (NoSuchAlgorithmException e) {
		FileLogger.log(e.toString(),"error");
  }

  /* Initializes this key generator for key size to 256. */
  keyGen.init(256);

  /* Generates a secret key */
  SecretKey secretKey = keyGen.generateKey();

  return secretKey;
 }

 public String run() {
  SecretKey secretKey = generateSecretKey();
  String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
 // System.out.println(encodedKey);
  return encodedKey;
  
 }
}
