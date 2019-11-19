package com.flexipgroup.app.sshconnect;
import java.io.File;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.util.Scanner;

import com.flexipgroup.app.sshconnect.Decryption;
import com.flexipgroup.app.sshconnect.Encryption;
import com.flexipgroup.app.sshconnect.KeyPairGeneration;
import com.flexipgroup.app.sshconnect.Keystore;


public class ClientSSHGeneration {

	private static char[] pwdArray = "password".toCharArray();
	private static char[] pwdArray2 = "passworde".toCharArray();
	private static File keyStoreFilePath;
	private static PrivateKey privateKey;
	private static PublicKey publicKey;
	
	private static KeyStore ks;
	private static String privateAlias = "privateAlias";
	private static KeyPair keypair;
	
	
	public static String path  = System.getProperty("user.home") + File.separator + "keypair/keypairclientstore";
	
	
	
	public ClientSSHGeneration () throws Exception
	{
		
		keyStoreFilePath = new File(path);
		
		System.out.println("..Generating ssh key");
		//generate key
		keypair = KeyPairGeneration.generateKeyPair();
		System.out.println("...key pair generated");
		
		System.out.println("...generating keystore");
		
		//generate keyStore
		ks = Keystore.createKeystore(pwdArray,keyStoreFilePath);
		System.out.println("...keystore generated - location private");
		
		
		
		privateKey = keypair.getPrivate();
		publicKey = keypair.getPublic();
		
		
		System.out.println("Saving private key to keyStore");
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter password to prevent anyone from accessing privateKey in keyStore");

		
		
		String password = input.nextLine();
		System.out.println("Enter alias to identify privateKey in keyStore");
		String alias = "";
		alias = input.nextLine();
		//store privateKey
		Keystore.storePrivateKey(alias, ks,keypair.getPrivate(),password.toCharArray());
		System.out.println("Private key stored successfully");
		
		//save public key to database
		
		
		
		checkPassword(input,password,alias);
		
		System.out.println("Private Key retrieved successfully");
		//encryption
		String cipherText = Encryption.encrypt("I am the lord", keypair.getPublic());
		
		 System.out.println(""+cipherText);
		
		//decryption
		String deciphered = Decryption.decrypt(cipherText, keypair.getPrivate());
		
		System.out.println(deciphered);
	}
	
	public void checkPassword(Scanner input,String password,String alias)throws Exception
	{
		System.out.println("Enter password to retrieve privateKey in keyStore");
		password="";
		password = input.nextLine();
		System.out.println("Enter alias for keyStore");
		alias = "";
		alias = input.nextLine();
		
		//readPrivateKey
	
			System.out.println("Private key : "+Keystore.readPrivateKeyByAlias(alias,ks,password.toCharArray()));
			System.out.println("Wrong password");
			System.out.println("Enter password to retrieve privateKey in keyStore");
			password="";
			password = input.nextLine();
			
			System.out.println("Enter alias for keyStore");
			alias = "";
			alias = input.nextLine();
			
			System.out.println("Private key : "+Keystore.readPrivateKeyByAlias(alias,ks,password.toCharArray()));
		
	}
	
	
	
}
