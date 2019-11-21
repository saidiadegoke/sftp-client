package com.flexipgroup.app.cipher;

/**
 * 
 * TODO
 * 
 * Implement this class to connect and send files to the SFTP server.
 *
 */
public class SFTPAgent {
	
	private String filePath;

	public SFTPAgent(String filePath) {
		this.filePath = filePath;
	}
	
	public void connect() {
		
		try {
			// TODO
			// try to connect so SFTP
		} catch (Exception e) {
			// Retry at 5 min intervals for 24 hours.
			// For now, you can just save the Log error to a file.
		}
		
	}
	
	public void send() {
		try {
			// TODO
			// Try sending the file here
		} catch (Exception e) {
			// Log operations
		}
	}

}
