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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.flexipgroup.app.cipher.CryptoEncrypt;
import com.flexipgroup.app.cipher.SFTPAgent;
import com.flexipgroup.app.common.FileUtils;
import com.flexipgroup.app.config.ConfigurationFile;
import com.flexipgroup.app.log.FileLogger;
import com.flexipgroup.reciever_client.RecieverClient;
import com.flexipgroup.sender_client.SenderClient;
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
	
	public void testRun() {
		ConfigurationFile config = new ConfigurationFile();
		FileUtils fileUtils = new FileUtils(filePath);
		String readFile = fileUtils.getDynamicReadFile();
		String archiveFile = fileUtils.getArchiveFile();
		FileManager.moveFile(fileUtils.getDownloadFile(), archiveFile);
		CryptoEncrypt encryptor = new CryptoEncrypt(config.SECRET_KEY, archiveFile, readFile);
		try {
			encryptor.encrypt();
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
		SFTPAgent agent = new SFTPAgent(readFile, config);
		try {
			agent.upload();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void runSFTP() throws Exception {
		ConfigurationFile config = new ConfigurationFile();
		FileUtils fileUtils = new FileUtils(filePath);
		String readFile = fileUtils.getDynamicReadFile();
		String archiveFile = fileUtils.getArchiveFile();
		FileManager.moveFile(fileUtils.getDownloadFile(), archiveFile);
		CryptoEncrypt encryptor = new CryptoEncrypt(config.SECRET_KEY, archiveFile, readFile);

		FileLogger Logger = new FileLogger();
		
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		try {
			
			encryptor.encrypt();
			new SenderClient(readFile).run();

			
			SenderClient sender = new SenderClient(readFile);
			
			Thread receiver = new RecieverClient();
			receiver.start();
			
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//RecieverClient receiver = new RecieverClient();
			//receiver.setName("receiver");
			//receiver.run();
			//receiver.join();
			
			//executorService.submit(sender);
			//executorService.submit(receiver);
			//executorService.shutdown();
			
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
