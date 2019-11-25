package com.flexipgroup.app;

import com.flexipgroup.app.config.ConfigurationFile;
import com.flexipgroup.app.log.FileLogger;
import com.flexipgroup.app.service.FileManager;

public class Application {

	public static void main(String[] args) {
		
		ConfigurationFile config = new ConfigurationFile();
		
		while(true) {
			/**
			 * 
			 * TODO
			 * 
			 * Within this infinite loop, the following should be done:
			 * 
			 * Get a singleton instance of the app.service.FileObserver
			 * Check is files exist to be processed. If no file do nothing. If files are found, do the following
			 * 
			 * 1. Get a singleton instance of the app.service.FileManager
			 * 2. Move a copy of the original file to records folder
			 * 3. Create an instance of the Crypto class
			 * 4. encrypt the file
			 * 5. Move the encrypted file to read folder for transfer
			 * 
			 * Transfer the file upstream by following the following steps:
			 * 
			 * 1. Create an instance of FileTransferService with the encryptedFilePath
			 * 2. Call its run() method
			 * 
			 */
			 FileLogger Logger = new FileLogger();
			 FileManager manage = new FileManager();
			 //manage.moveFile( , destinationFile);
			 
			 Logger.log("Watch file changes on FileObserver","error" );
			try {
				Thread.sleep(config.MAIN_POLL_INTERVAL);
			} catch (InterruptedException e) {
				// TODO 
				// Log everything happening, especially the errors.
				e.printStackTrace();
			}
		}

	}

}
