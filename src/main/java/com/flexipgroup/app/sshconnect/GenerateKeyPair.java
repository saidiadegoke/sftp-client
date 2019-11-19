package com.flexipgroup.app.sshconnect;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;

public class GenerateKeyPair {
	
	public static void main (String [] args)throws Exception
	{
		//Generate Key pair
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
	    generator.initialize(2048, new SecureRandom());
	    KeyPair pair = generator.generateKeyPair();
	    
	    
	}

}
