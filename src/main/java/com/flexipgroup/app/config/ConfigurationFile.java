package com.flexipgroup.app.config;

import java.io.File;
import java.io.IOException;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

/**
 * 
 * TODO
 * 
 * Read the config file and populate this file with corresponding fields form the config file;
 *
 */

public class ConfigurationFile {
	
	public String SFTP_PASSWORD;
	public int SFTP_PORT_NUMBER;
	public int MAIN_POLL_INTERVAL;
	
	public ConfigurationFile() {
		try {
			run("config.ini");
		} catch (InvalidFileFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void run(String filePath) throws InvalidFileFormatException, IOException {
		Wini ini = new Wini(new File(filePath));
        //int port = ini.get("port", "22", int.class);
        //double height = ini.get("owner", "height", double.class);
        SFTP_PASSWORD = ini.get("sftp", "password");
        MAIN_POLL_INTERVAL = ini.get("app", "main_poll_interval", int.class);

	}

}
