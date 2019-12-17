package com.flexipgroup.app;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.flexipgroup.app.config.ConfigurationFile;
import com.flexipgroup.app.log.FileLogger;
import com.flexipgroup.sender_client.SenderClient;


public class Tester {

	public static void main(String[] args) throws IOException, TimeoutException {
		ConfigurationFile config = new ConfigurationFile();
		//new SenderClient(config.FILEPATH).run();
		new SenderClient("C:\\Users\\ANGER DOOSHIMA LOIS\\Desktop\\Sample.xlsx").run();

		//String path = config.FILEPATH;
		//new SenderClient().run();
		//new SenderClient().run();

	}	
	
	public static void main1(String[] args) throws IOException, TimeoutException {
		ConfigurationFile config = new ConfigurationFile();

		FileLogger logger = new FileLogger();
		//logger.log();
		
		//new SenderClient().run();

	}

}
