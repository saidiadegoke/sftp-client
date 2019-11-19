package com.flexipgroup.app.sshconnect;

import java.security.PrivateKey;
import java.security.Signature;
import java.util.Base64;

public class Sign {
	
	public static String sign(String plainText, PrivateKey privateKey) throws Exception {
	    Signature privateSignature = Signature.getInstance("SHA256withRSA");
	    privateSignature.initSign(privateKey);
	    privateSignature.update(plainText.getBytes("UTF-8"));

	    byte[] signature = privateSignature.sign();

	    return Base64.getEncoder().encodeToString(signature);
	}

}
