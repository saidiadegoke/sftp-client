package com.flexipgroup.reciever_client;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.flexipgroup.reciever_context.RecieverContext;
import com.flexipgroup.reciever_strategy.RecieverStrategy;



public class RecieverClient {
String filePath;
	

	public RecieverClient(String filePath) {
		this.filePath = filePath;
	}
	
	public static void main(String[] args) throws IOException, TimeoutException {
		new RecieverClient("C:\\Users\\ANGER DOOSHIMA LOIS\\Desktop\\newExcel\\Sample.xlsx").run();
	}	
	
	public  void run() throws IOException, TimeoutException {

		RecieverContext context = new RecieverContext();
		RecieverStrategy reciever = new RecieverFactory(new RecieverMessagingFile(filePath)).getInstance();
		
		context.setStrategy(reciever);
		context.execute();         
	}
}
