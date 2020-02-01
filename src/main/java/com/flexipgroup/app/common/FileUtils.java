package com.flexipgroup.app.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneId;

import com.flexipgroup.app.config.ConfigurationFile;

public class FileUtils {
	String fileName;
	private ConfigurationFile config;
	
	
	public FileUtils(String fileName) {
		this.fileName = fileName;
		
		this.config = new ConfigurationFile();
	}
	
	public FileUtils() {
		this.config = new ConfigurationFile();
	}
	
	public static byte[] fileToBytes(String filePath) {
		try {
			return Files.readAllBytes(Paths.get(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getReadFile() {
		
		File f = new File(fileName);
		String ext = fileName.substring(fileName.lastIndexOf("."));
		LocalDateTime date = LocalDateTime.now();
		ZoneId zoneId = ZoneId.systemDefault();
		long epoch = date.atZone(zoneId).toEpochSecond();
		String readFile = config.BASEPATH + File.separator + config.READ_FOLDER + File.separator + f.getName();
		
		return readFile;
	}
	
	public String getDynamicReadFile() {
		String rext = config.RECEIVER_EXTENSION;
		String ext = fileName.substring(fileName.lastIndexOf(".")) + "." + rext;
		LocalDateTime date = LocalDateTime.now();
		ZoneId zoneId = ZoneId.systemDefault();
		long epoch = date.atZone(zoneId).toEpochSecond();
		return config.BASEPATH + File.separator + config.READ_FOLDER + File.separator + config.CUSTOMER_ID + "-payments-" + epoch + ext;
	}
	
	public String getDownloadFile() {
		return config.BASEPATH + File.separatorChar + config.DOWNLOAD_FOLDER + File.separatorChar + fileName;
	}
	
	public String getArchiveFile() {
		
		File f = new File(fileName);
		String ext = fileName.substring(fileName.lastIndexOf("."));
		LocalDateTime date = LocalDateTime.now();
		ZoneId zoneId = ZoneId.systemDefault();
		long epoch = date.atZone(zoneId).toEpochSecond();
		String archiveFile = config.BASEPATH + File.separator + config.ARCHIVE_FOLDER + File.separator + config.CUSTOMER_ID + "-payments-" + epoch + ext;

		return archiveFile;
	}
	
	public String getReceiverFile() {
		//ConfigurationFile config = new ConfigurationFile();
		String ext = config.FILE_EXTENSION + "." + config.RECEIVER_EXTENSION;
		LocalDateTime date = LocalDateTime.now();
		ZoneId zoneId = ZoneId.systemDefault();
		long epoch = date.atZone(zoneId).toEpochSecond();
		String receiverFile = config.BASEPATH + File.separator + config.RECEIVER_FOLDER + File.separator + config.CUSTOMER_ID + "-payments-" + epoch + "." + ext;

		return receiverFile;
	}

}
