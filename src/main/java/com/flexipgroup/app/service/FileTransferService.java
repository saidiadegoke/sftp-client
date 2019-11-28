package com.flexipgroup.app.service;

import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.flexipgroup.app.cipher.CryptoEncrypt;
import com.flexipgroup.app.cipher.SFTPAgent;
import com.flexipgroup.app.config.ConfigurationFile;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

/**
 * 
 * The purpose of this service is to delegate the sending of the file upstream to SFTPAgent
 * for connecting and sending to the server.
 *
 */
public class FileTransferService {
	
	private String filePath;

	public FileTransferService(String filePath) {
		this.filePath = filePath;
	}
	
	public void run() {
		try {
			runSFTP();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void runSFTP() throws Exception {
		ConfigurationFile config = new ConfigurationFile();
		File f = new File(filePath);
		String ext = filePath.substring(filePath.lastIndexOf("."));
		LocalDateTime date = LocalDateTime.now();
		ZoneId zoneId = ZoneId.systemDefault(); // or: ZoneId.of("Europe/Oslo");
		long epoch = date.atZone(zoneId).toEpochSecond(); //date.atStartOfDay(zoneId).toEpochSecond();
		String readFile = config.BASEPATH + File.separator + config.READ_FOLDER + File.separator + config.CUSTOMER_ID + "-payment-" + epoch + ext + ".aes";
		String archiveFile = config.BASEPATH + File.separator + config.ARCHIVE_FOLDER + File.separator + config.CUSTOMER_ID + "-" + epoch + f.getName();
		FileManager.moveFile(filePath, archiveFile);
		CryptoEncrypt encryptor = new CryptoEncrypt(config.SECRET_KEY, archiveFile, readFile);
		try {
			encryptor.encrypt();
			SFTPAgent agent = new SFTPAgent(readFile, config);
			
			try {
				agent.upload();
			} catch (JSchException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SftpException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (InvalidKeyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidKeySpecException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchPaddingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidParameterSpecException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalBlockSizeException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (BadPaddingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

}
