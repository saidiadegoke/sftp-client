package com.flexipgroup.sender_impli;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
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

public class XLSXSender implements SenderStrategy {
	private  MessagingFile files;
	 private static final Logger LOGGER = Logger.getLogger(SenderClient.class);

	public XLSXSender() {}
	
	public XLSXSender(MessagingFile files) {
		this.files = files;
	}



public void execute() throws IOException, TimeoutException {
		//create a new connection to the message mq server 		 
		 ConnectionFactory factory = new ConnectionFactory();
		 
		 try (Connection connection = factory.newConnection();
		      Channel channel = connection.createChannel()) {
			 
			 channel.queueDeclare("key5000", false, false, false, null);
			
		     Workbook workbook = WorkbookFactory.create(new File(files.getUrl()));				
		     
			    ByteArrayOutputStream bos = new ByteArrayOutputStream();
				 workbook.write(bos);

						
			    byte[] excelBytes = bos.toByteArray();
				channel.basicPublish("","key5000",false, null,excelBytes);

				 //String filing = bytesarray.toString();
				LOGGER.info("excel path [Byte Format] : " +  excelBytes);
				LOGGER.error("excel path [Byte Format] : " +  excelBytes);
				LOGGER.warn("excel path [Byte Format] : " +  excelBytes);
				LOGGER.trace("excel path [Byte Format] : " +  excelBytes);
				LOGGER.fatal("excel path [Byte Format] : " +  excelBytes);


		} catch (EncryptedDocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InvalidFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
