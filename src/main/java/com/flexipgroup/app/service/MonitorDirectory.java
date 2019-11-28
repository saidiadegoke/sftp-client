package com.flexipgroup.app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import com.flexipgroup.app.cipher.SFTPAgent;
import com.flexipgroup.app.config.ConfigurationFile;

public class MonitorDirectory {

	public static void main1(String[] args) throws IOException,
			InterruptedException {
		ConfigurationFile config = new ConfigurationFile();

		Path faxFolder = Paths.get(config.BASEPATH + File.separator + config.DOWNLOAD_FOLDER);
		WatchService watchService = FileSystems.getDefault().newWatchService();
		faxFolder.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

		boolean valid = true;
		do {
			WatchKey watchKey = watchService.take();

			for (WatchEvent event : watchKey.pollEvents()) {
				WatchEvent.Kind kind = event.kind();
				if (StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())) {
					String fileName = event.context().toString();
					FileTransferService transfer = new FileTransferService(config.BASEPATH + File.separator + config.DOWNLOAD_FOLDER + File.separator + fileName);
					transfer.run();
					System.out.println("File Created:" + fileName);
				}
			}
			valid = watchKey.reset();

		} while (valid);

	}
}
