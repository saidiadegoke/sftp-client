package com.flexipgroup.app.sshconnect;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;

public class Keystore {
	
	
	public static String path  = System.getProperty("user.home") + File.separator + "keypair/";
	
	public static KeyStore createKeystore(char[] pwdArray, File keyStoreFilePath) throws 
	KeyStoreException,CertificateException,NoSuchAlgorithmException,
	IOException
	{
		KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
		
		ks.load(null, pwdArray);
		
		
		//store keyStore
		FileOutputStream fos = new FileOutputStream(keyStoreFilePath);
		ks.store(fos,pwdArray);
		return ks;
	}
	
	//load keyStore
	public static KeyStore loadKeyStore(File keyStoreFilePath, char[] pwdArray)throws 
		KeyStoreException,CertificateException,NoSuchAlgorithmException,
		IOException
		{
			KeyStore ks = KeyStore.getInstance("JKS");
			ks.load(new FileInputStream(keyStoreFilePath),pwdArray);
			return  ks;
	}
	
	
	public static boolean storePrivateKey(String privateAlias,KeyStore ks,PrivateKey privateKey,char[] pwdArray)throws 
	KeyStoreException,CertificateException,NoSuchAlgorithmException,
	IOException
	{
		X509Certificate[] certificateChain = new X509Certificate[2];
		ks.setKeyEntry(privateAlias,privateKey,pwdArray,certificateChain);
		return ks.isKeyEntry(privateAlias);
	}
		
	//reading a single entry
	public static PrivateKey readPrivateKeyByAlias (String privateAlias,KeyStore ks,char[] pwdArray)throws 
	KeyStoreException,CertificateException,NoSuchAlgorithmException,
	IOException, UnrecoverableKeyException
	{
		
		PrivateKey ssoSigningKey = (PrivateKey) ks.getKey(privateAlias, pwdArray);
		return ssoSigningKey;
		
	}
		
	

}
