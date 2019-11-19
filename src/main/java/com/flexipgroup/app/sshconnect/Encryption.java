package com.flexipgroup.app.sshconnect;

import java.security.PublicKey;
import java.util.Base64;

import javax.crypto.Cipher;

public class Encryption {
	
	public static String encrypt(String plainText, PublicKey publicKey) throws Exception {
	    Cipher encryptCipher = Cipher.getInstance("RSA");
	    encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

	    byte[] cipherText = encryptCipher.doFinal(plainText.getBytes("UTF-8"));

	    return Base64.getEncoder().encodeToString(cipherText);
	}

}
