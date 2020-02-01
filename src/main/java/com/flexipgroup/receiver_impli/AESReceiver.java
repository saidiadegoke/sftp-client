package com.flexipgroup.receiver_impli;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import com.flexipgroup.app.config.ConfigurationFile;
import com.flexipgroup.reciever_client.RecieverMessagingFile;
import com.flexipgroup.reciever_strategy.RecieverStrategy;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class AESReceiver implements RecieverStrategy {

	private RecieverMessagingFile file;
	String TASK_QUEUE_NAME = "task_queue";
	
	private Channel channel;
	private ConfigurationFile config = null;

	public AESReceiver() {
		this.config = new ConfigurationFile();
		TASK_QUEUE_NAME = config.ROUTING_ID;
	}
	
	public AESReceiver(RecieverMessagingFile file) {
		this.file = file;
		System.out.println("New path in AESR " + file.getUrl());
		this.config = new ConfigurationFile();
		TASK_QUEUE_NAME = config.ROUTING_ID;
	}

	public  void execute() throws IOException, TimeoutException {
		//String filePath = new RecieverMessagingFile(file.getUrl()).getUrl();
		//open up  the connection to the message mq server 		 
		 
			  Channel ch = getConnection();
			  //ch.basicConsume(, false, deliverCallback, consumerTag -> { });
			  ch.basicConsume(TASK_QUEUE_NAME, false, (consumerTag, message)->{
				  
				  String uniqueID = UUID.randomUUID().toString();
				  
				  String fileUrl = System.getProperty("user.home") + "/flexclient/receiver/" + uniqueID + "." + config.FILE_EXTENSION + ".aes"; 
				  FileOutputStream out = new FileOutputStream(fileUrl);
				  System.out.println("File url=>: " + fileUrl);
				  try {
		           
		            out.write(message.getBody());	
		            
		        }  catch (Exception e) {
		        
		           e.printStackTrace();
		        } finally {
		        	out.close();
		        	ch.basicAck(message.getEnvelope().getDeliveryTag(), false);
		        	System.out.println("Send ACK to RabbitMQ");
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
			//channel.queueDeclare("key5000", false, false, false, null);
			channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
			channel.basicQos(1);
			return channel;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	     
		  
		return null;
	}
	
}