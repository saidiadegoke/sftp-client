package com.flexipgroup.sender_client;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.*;

import com.flexipgroup.sender_context.SenderContext;
import com.flexipgroup.sender_strategy.SenderStrategy;


public class SenderClient {
	 String filePath;
	 private static final Logger LOGGER = Logger.getLogger(SenderClient.class);
	 
	public SenderClient(String filePath) {
		this.filePath = filePath;
	}
	
	public static void main1(String[] args) throws IOException, TimeoutException {
		new SenderClient("C:\\Users\\ANGER DOOSHIMA LOIS\\Desktop\\Sample.xlsx").run();
		
	}	
	
	public  void run() throws IOException, TimeoutException {

		SenderContext context = new SenderContext();
		SenderStrategy sender = new SenderFactory(new MessagingFile(filePath)).getInstance();
		
		context.setStrategy(sender);
		context.execute();         
	}
}


