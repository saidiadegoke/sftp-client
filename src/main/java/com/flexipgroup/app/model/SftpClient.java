package com.flexipgroup.app.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;

import lombok.Data;


@Data
public class SftpClient implements Serializable{
	
	private static final long serialVersionUID = 8614348486957130685L;

	private File localFile;
	
	private File remoteFile;
	
	private FileInputStream fileInputStream;
	
	private FileOutputStream fileOutputStream;
	
	private long max;
	
	private long count;
	
	private long percent;
	
	private long megabytes;
	
	//private String privateKey;

	private String host;

	private String username;

	private String password;

	private String passPhrase;

	private String knownHosts;

	private int port;
	
	private boolean connected;
	
	private String message;
	
	private PublicKey publicKey;
	
	private PrivateKey privateKey;
	
	private KeyStore keyStore;
	
	private File keyStoreFilePath;
	
	private String keyStorePassword;
	
	private KeyPair keypair;
	
	private String privateKeyStorePassword;
	
	private String aliasPrivateKeyStore;
	
	private File pollingFilePath;
	
	private File uploadFile;
	
	private String pollingFilePathMessage;
	
	//connect to server
	public SftpClient(String username,String password, String host, int port)
	{
		this.username = username;
		this.password = password;
		this.host = host;
		this.port = port;
	}
	
	public SftpClient() {}
	
	//transfer public key to server
	
	
	
	//upload file
	
	//download file
	
}
