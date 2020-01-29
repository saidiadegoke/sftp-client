package com.flexipgroup.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.flexipgroup.app.cipher.SFTPAgent;
import com.flexipgroup.app.common.FileUtils;
import com.flexipgroup.app.config.ConfigurationFile;
import com.flexipgroup.app.log.FileLogger;
import com.flexipgroup.app.service.FileManager;
import com.flexipgroup.app.service.FileTransferService;
import com.flexipgroup.reciever_client.RecieverClient;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;

public class Application {

	public static void main(String[] args) throws IOException, InterruptedException {
		ConfigurationFile config = new ConfigurationFile();

		Path faxFolder = Paths.get(config.BASEPATH + File.separatorChar + config.DOWNLOAD_FOLDER);
		WatchService watchService = FileSystems.getDefault().newWatchService();
		faxFolder.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);
		//Thread receiver = new RecieverClient();
		//receiver.start();
		//FileLogger Logger = new FileLogger();

		FileLogger.log("Watch file lannnnn", "info");

		String[] paths = {
				
				
				config.UPLOAD_FOLDER,
				config.READ_FOLDER,
				config.ERROR_FOLDER,
				config.ARCHIVE_FOLDER,
				config.SUCCESS_FOLDER
		};
		
//		try {
//			run(paths, config);
//		} catch (JSchException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SftpException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try {
			if(config.CREATE_REMOTE_FOLDERS == true) {
				run(paths, config);
			}
		} catch (JSchException e) {
			FileLogger.log(e.toString(),"error");

		} catch (SftpException e) {
			FileLogger.log(e.toString(),"error");

		}
		
		try (Stream<Path> walk = Files.walk(Paths.get(config.BASEPATH + File.separatorChar + config.DOWNLOAD_FOLDER))) {

			List<String> result = walk.filter(Files::isRegularFile)
					.map(x -> x.toString()).collect(Collectors.toList());

			//result.forEach(System.out::println);

			for(String file: result) {
				File f = new File(file);
				f.getName();
				String loc = f.getAbsolutePath().substring(0, f.getAbsolutePath().lastIndexOf(File.separatorChar));
				String newLoc = loc + File.separatorChar + new Random().nextDouble() + f.getName();
				
				System.out.println(newLoc);
				Files.move(Paths.get(file), Paths.get(newLoc));
			}

		} catch (IOException e) {
			FileLogger.log(e.toString(),"error");
		}

		boolean valid = true;
		do {
			WatchKey watchKey = watchService.take();

			for (WatchEvent event : watchKey.pollEvents()) {
				WatchEvent.Kind kind = event.kind();
				if                                                                                                                                     (StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())) {
					String fileName = event.context().toString();
					
					FileTransferService transfer = new FileTransferService(
							fileName);
					//transfer.setName("FileTransfer");
					transfer.run();
					System.out.println("File Created:" + fileName);
				}
			}
			valid = watchKey.reset();

		} while (valid);
		
		

	}
	
	public static void run(String[] paths, ConfigurationFile config) throws JSchException, FileNotFoundException, SftpException, IOException {
		
		  SFTPAgent agent = new SFTPAgent("", config); ChannelSftp channelSftp =
		  agent.setupJsch(); channelSftp.connect();
		  
		  agent.prepareUpload(channelSftp, config.SFTP_REMOTE_PATH, paths, false);
		 
		
	}

	public static void main1(String[] args) {

		ConfigurationFile config = new ConfigurationFile();

		while (true) {
			/**
			 * 
			 * TODO
			 * 
			 * Within this infinite loop, the following should be done:
			 * 
			 * Get a singleton instance of the app.service.FileObserver Check is files exist
			 * to be processed. If no file do nothing. If files are found, do the following
			 * 
			 * 1. Get a singleton instance of the app.service.FileManager 2. Move a copy of
			 * the original file to records folder 3. Create an instance of the Crypto class
			 * 4. encrypt the file 5. Move the encrypted file to read folder for transfer
			 * 
			 * Transfer the file upstream by following the following steps:
			 * 
			 * 1. Create an instance of FileTransferService with the encryptedFilePath 2.
			 * Call its run() method
			 * 
			 */
			
			//FileLogger Logger = new FileLogger();
			//FileManager manage = new FileManager();
			// manage.moveFile( , destinationFile);

			FileLogger.log("Watch file changes on FileObserver", "error");
			FileLogger.log("Watch file lannnnn", "info");

			try {
				Thread.sleep(config.MAIN_POLL_INTERVAL);
			} catch (InterruptedException e) {
				// TODO 
				// Log everything happening, especially the errors.
				
				FileLogger.log(e.toString(), "error");

			}
		}

	}

}
