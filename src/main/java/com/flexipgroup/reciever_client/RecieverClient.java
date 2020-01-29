package com.flexipgroup.reciever_client;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.flexipgroup.app.cipher.SFTPAgent;
import com.flexipgroup.app.common.FileUtils;
import com.flexipgroup.app.config.ConfigurationFile;
import com.flexipgroup.app.log.FileLogger;
import com.flexipgroup.reciever_context.RecieverContext;




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
			
			new RecieverFactory(new RecieverMessagingFile(filePath)).getInstance().execute();
			
			File f = new File(filePath);
			if(f.exists()) {
		  SFTPAgent agent = new SFTPAgent(filePath, new ConfigurationFile()); 
		  agent.upload(); 
			} else {
				System.out.println("Consumed file not found!");
			}
			 
			
			System.out.println("File path: " + filePath);
		} catch (IOException e) {
			FileLogger.log(e.toString(),"error");

		} catch (TimeoutException e) {
			FileLogger.log(e.toString(),"error");

		} catch (Exception e) {
			FileLogger.log(e.toString(),"error");
		}
	}
}
