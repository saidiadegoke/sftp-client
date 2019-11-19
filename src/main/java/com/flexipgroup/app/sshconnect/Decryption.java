package com.flexipgroup.app.sshconnect;

import java.security.PrivateKey;
import java.util.Base64;

import javax.crypto.Cipher;

public class Decryption {
	
	public static String decrypt(String cipherText, PrivateKey privateKey) throws Exception {
	    byte[] bytes = Base64.getDecoder().decode(cipherText);

	    Cipher decriptCipher = Cipher.getInstance("RSA");
	    decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);

	    return new String(decriptCipher.doFinal(bytes), "UTF-8");
	}

}
