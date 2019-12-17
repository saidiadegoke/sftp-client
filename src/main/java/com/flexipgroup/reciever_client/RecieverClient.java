package com.flexipgroup.reciever_client;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.flexipgroup.app.cipher.SFTPAgent;
import com.flexipgroup.app.common.FileUtils;
import com.flexipgroup.app.config.ConfigurationFile;
import com.flexipgroup.receiver_impli.AESReceiver;
import com.flexipgroup.reciever_context.RecieverContext;
import com.flexipgroup.reciever_strategy.RecieverStrategy;



public class RecieverClient extends Thread {
	private String filePath;
	

	public RecieverClient() {
		//this.filePath = filePath;
		FileUtils fileUtils = new FileUtils(filePath);
		this.filePath = fileUtils.getReceiverFile();
	}
	
	public static void main1(String[] args) throws IOException, TimeoutException {
		//new RecieverClient("C:\\Users\\ANGER DOOSHIMA LOIS\\Desktop\\newExcel\\Sample.xlsx").run();
	}	
	
	public  void run() {

		try {
			
			RecieverContext context = new RecieverContext();
			//RecieverStrategy receiver = new RecieverFactory(new RecieverMessagingFile(filePath)).getInstance();
			//context.setStrategy(receiver);
			//context.execute();
			//receiver.execute();
			new RecieverFactory(new RecieverMessagingFile(filePath)).getInstance().execute();
			
			File f = new File(filePath);
			if(f.exists()) {
		  SFTPAgent agent = new SFTPAgent(filePath, new ConfigurationFile()); 
		  agent.upload(); 
			}
			 
			
			System.out.println("File path: " + filePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
