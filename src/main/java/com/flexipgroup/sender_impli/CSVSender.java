package com.flexipgroup.sender_impli;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.flexipgroup.sender_client.MessagingFile;
import com.flexipgroup.sender_strategy.SenderStrategy;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class CSVSender implements SenderStrategy {

	private  MessagingFile files;
	
	public CSVSender(MessagingFile files) {
		this.files = files;
		System.out.println(files);
	}
	


	public void execute() throws IOException, TimeoutException {
		//create a new connection to the message mq server 		 
		 ConnectionFactory factory = new ConnectionFactory();
		 
		 try (Connection connection = factory.newConnection();
		      Channel channel = connection.createChannel()) {
			 
			 channel.queueDeclare("key5001", false, false, false, null);
			 
		//implement CSV specific functionality
		
		 File file = new File(files.getUrl()); 
		
		byte[] bytesarray = new byte[(int) file.length()];
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();

		  try {
			bos.write(bytesarray);
				
	    byte[] excelBytes = bos.toByteArray();
		channel.basicPublish("","key5001",false, null,excelBytes);

		 String filing = bytesarray.toString();
		System.out.println("excel path [Byte Format] : " +  excelBytes);

	   }catch(Exception e) {


	    }
	}
	
	}
}
