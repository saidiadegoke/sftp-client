package com.flexipgroup.receiver_impli;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.flexipgroup.app.log.FileLogger;
import com.flexipgroup.reciever_client.RecieverMessagingFile;
import com.flexipgroup.reciever_strategy.RecieverStrategy;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class AESReceiver implements RecieverStrategy {

	private RecieverMessagingFile file;
	
	private Channel channel;

	public AESReceiver() {}
	
	public AESReceiver(RecieverMessagingFile file) {
		this.file = file;
		System.out.println("New path in AESR " + file.getUrl());
	}

	public  void execute() throws IOException, TimeoutException {
		//String filePath = new RecieverMessagingFile(file.getUrl()).getUrl();
		//open up  the connection to the message mq server 		 
		 
			  Channel ch = getConnection();
			  ch.basicConsume("key5000", true, (consumerTag, message)->{

				  try {
		            
		            FileOutputStream out = new FileOutputStream(file.getUrl());
		            out.write(message.getBody());

		              out.close();
			        System.out.println("I just got a new file = " + file.getUrl());	

		            //System.out.println("I just got the message for that thing = " + out);	
		            
		            
		        } catch (Exception e) {
		        	FileLogger.log("error has occurred", e.toString());
		         
		        }
				  
			  }, consumerTag -> {});
			  
		 }
	
	private Channel getConnection() {
		if(channel != null) {
			return channel;
		}
		
		ConnectionFactory factory = new ConnectionFactory();
		 
	 	  Connection connection;
	 	  
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
			channel.queueDeclare("key5000", false, false, false, null);
			return channel;
		} catch (IOException e) {
			FileLogger.log(e.toString(), "error");
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	     
		  
		return null;
	}
	
}