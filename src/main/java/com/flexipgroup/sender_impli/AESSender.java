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

import com.flexipgroup.app.config.ConfigurationFile;
import com.flexipgroup.sender_client.MessagingFile;
import com.flexipgroup.sender_client.SenderClient;
import com.flexipgroup.sender_strategy.SenderStrategy;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class AESSender implements SenderStrategy {
	private  MessagingFile file;
	//private static final String TASK_QUEUE_NAME = "task_queue";
	private static final Logger LOGGER = Logger.getLogger(SenderClient.class);
	
	public AESSender(MessagingFile file) {
		this.file = file;
	}



	public void execute() throws IOException, TimeoutException {
		ConfigurationFile config = new ConfigurationFile();
		String TASK_QUEUE_NAME = config.ROUTING_ID;
		//create a new connection to the message mq server 		 
		 ConnectionFactory factory = new ConnectionFactory();
		 
		 try (Connection connection = factory.newConnection();
		      Channel channel = connection.createChannel()) {
			 
			 //channel.queueDeclare("key5000", false, false, false, null);
			 channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
		     
		     byte[] fileByte = Files.readAllBytes(file.getPath());

			 //channel.basicPublish("","key5000",false, null,fileByte);
			 channel.basicPublish("", TASK_QUEUE_NAME,
		                MessageProperties.PERSISTENT_TEXT_PLAIN,
		                fileByte);

			 //String filing = bytesarray.toString();
			 LOGGER.info("excel path [Byte Format] : " +  fileByte);


		} catch (EncryptedDocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
