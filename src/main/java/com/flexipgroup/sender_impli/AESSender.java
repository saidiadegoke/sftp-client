package com.flexipgroup.sender_impli;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

public class AESSender implements SenderStrategy  {
	private  MessagingFile filePath;
	 private static final Logger LOGGER = Logger.getLogger(SenderClient.class);

	public AESSender() {}
	
	public AESSender(MessagingFile files) {
		this.filePath = files;
	}



public void execute() throws IOException, TimeoutException {
		//create a new connection to the message mq server 		 
		 ConnectionFactory factory = new ConnectionFactory();
		 
		 try (Connection connection = factory.newConnection();
		      Channel channel = connection.createChannel()) {
			 
			 channel.queueDeclare("key5780", false, false, false, null);
			  File file = new File(filePath.getUrl());				

			  byte[] bytesArray = new byte[(int) file.length()]; 

			  FileInputStream fis = new FileInputStream(file);
			  fis.read(bytesArray); //read file into bytes[]
			  //fis.close();
						
				channel.basicPublish("","key5780",false, null,bytesArray);

				 //String filing = bytesarray.toString();
				LOGGER.info("excel path [Byte Format] : " +  bytesArray);
				LOGGER.error("excel path [Byte Format] : " +  bytesArray);
				LOGGER.warn("excel path [Byte Format] : " +  bytesArray);
				LOGGER.trace("excel path [Byte Format] : " +  bytesArray);
				LOGGER.fatal("excel path [Byte Format] : " +  bytesArray);


		} catch (EncryptedDocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		
		}
}	 
}

