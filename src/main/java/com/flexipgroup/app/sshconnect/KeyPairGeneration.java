package com.flexipgroup.app.sshconnect;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

public class KeyPairGeneration {
	
	
	public static KeyPair generateKeyPair() throws Exception{
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
	    generator.initialize(2048, new SecureRandom());
	    KeyPair pair = generator.generateKeyPair();
	    
	    return pair;
	}
	
}
