package com.flexipgroup.app;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.flexipgroup.app.common.FileUtils;
import com.flexipgroup.app.config.ConfigurationFile;
import com.flexipgroup.app.log.FileLogger;
import com.flexipgroup.reciever_client.RecieverClient;
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
		FileUtils fileUtils = new FileUtils("userID-payment-1574868145.txt.aes");

		//FileLogger logger = new FileLogger();
		//logger.log();
		
		//new SenderClient().run();
		
		String filePath = fileUtils.getReadFile();
		//Thread t1 = new SenderClient(filePath);
		//t1.start();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String filePath1 = fileUtils.getReceiverFile();
		//Thread t2 = new RecieverClient();
		//t2.start();
		
		
		System.out.println(fileUtils.getReceiverFile());
		

	}

}
