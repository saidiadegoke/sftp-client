package com.flexipgroup.sender_client;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.*;

import com.flexipgroup.app.config.ConfigurationFile;
import com.flexipgroup.sender_context.SenderContext;
import com.flexipgroup.sender_strategy.SenderStrategy;

public class SenderClient {
	 String filePath;
	 private static final Logger LOGGER = Logger.getLogger(SenderClient.class);
	 ConfigurationFile config = new ConfigurationFile();

//	 
//	public SenderClient(String filePath) {
//		this.filePath = filePath;
//	}
	
	public SenderClient(String filePath) {
		this.filePath = filePath;

	}
	

	public void run() throws IOException, TimeoutException {

		SenderContext context = new SenderContext();
		System.out.println(filePath);
		SenderStrategy sender = new SenderFactory(new MessagingFile(filePath)).getInstance();

		context.setStrategy(sender);
		context.execute();         
	}
}


