package com.flexipgroup.app.sshconnect;

import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

public class Verify {
	
	public static boolean verify(String plainText, String signature, PublicKey publicKey) throws Exception {
	    Signature publicSignature = Signature.getInstance("SHA256withRSA");
	    publicSignature.initVerify(publicKey);
	    publicSignature.update(plainText.getBytes("UTF-8"));

	    byte[] signatureBytes = Base64.getDecoder().decode(signature);

	    return publicSignature.verify(signatureBytes);
	}

}
