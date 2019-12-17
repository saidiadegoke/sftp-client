package com.flexipgroup.sender_impli;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.concurrent.TimeoutException;

import org.apache.log4j.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.flexipgroup.sender_client.MessagingFile;
import com.flexipgroup.sender_client.SenderClient;
import com.flexipgroup.sender_strategy.SenderStrategy;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class AESSender implements SenderStrategy {
	private  MessagingFile file;
	 private static final Logger LOGGER = Logger.getLogger(SenderClient.class);
	
	public AESSender(MessagingFile file) {
		this.file = file;
	}



public void execute() throws IOException, TimeoutException {
		//create a new connection to the message mq server 		 
		 ConnectionFactory factory = new ConnectionFactory();
		 
		 try (Connection connection = factory.newConnection();
		      Channel channel = connection.createChannel()) {
			 
			 channel.queueDeclare("key5000", false, false, false, null);
		     
		     byte[] fileByte = Files.readAllBytes(file.getPath());

			 channel.basicPublish("","key5000",false, null,fileByte);

			 //String filing = bytesarray.toString();
			 LOGGER.info("excel path [Byte Format] : " +  fileByte);


		} catch (EncryptedDocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
