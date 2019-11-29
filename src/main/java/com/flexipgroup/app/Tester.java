package com.flexipgroup.app;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.flexipgroup.app.config.ConfigurationFile;
import com.flexipgroup.app.log.FileLogger;
import com.flexipgroup.sender_client.SenderClient;


public class Tester {
	public static void main1(String[] args) throws IOException, TimeoutException {
		ConfigurationFile config = new ConfigurationFile();

		FileLogger logger = new FileLogger();
		//logger.log();
		
		//new SenderClient().run();

	}
}
