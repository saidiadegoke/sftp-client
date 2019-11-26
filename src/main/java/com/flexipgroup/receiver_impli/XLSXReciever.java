package com.flexipgroup.receiver_impli;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.flexipgroup.reciever_client.RecieverMessagingFile;
import com.flexipgroup.reciever_strategy.RecieverStrategy;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class XLSXReciever implements RecieverStrategy{

	private  RecieverMessagingFile files;

	public XLSXReciever() {}
	
	public XLSXReciever(RecieverMessagingFile files) {
		this.files = files;
	}

	public  void execute() throws IOException, TimeoutException {


		//open up  the connection to the message mq server 		 
		 ConnectionFactory factory = new ConnectionFactory();
		 
		 
		 	  Connection connection = factory.newConnection();
		      Channel channel = connection.createChannel();
			  channel.queueDeclare("key5000", false, false, false, null);
			  
			  channel.basicConsume("key5000", true, (consumerTag, message)->{

				  try {
		            
		            FileOutputStream out = new FileOutputStream(files.getUrl());
		            out.write(message.getBody());

		              out.close();
			        System.out.println("I just got the message for that thing = " + message.getBody());	

		            System.out.println("I just got the message for that thing = " + out);	
		            
		            
		        } catch (Exception e) {
		           e.printStackTrace();
		        }
				  

				
			  }, consumerTag -> {});
			  
		 }
	
}
