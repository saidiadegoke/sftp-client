package com.flexipgroup.app.service;

import com.flexipgroup.app.cipher.SFTPAgent;

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
		runSFTP();
	}
	
	public void runSFTP() {
		SFTPAgent agent = new SFTPAgent(filePath);
	}

}
