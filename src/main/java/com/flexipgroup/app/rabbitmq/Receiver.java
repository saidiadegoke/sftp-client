package com.flexipgroup.app.rabbitmq;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import com.flexipgroup.app.cipher.SFTPAgent;
import com.flexipgroup.app.common.FileUtils;
import com.flexipgroup.app.common.RabbitMQConnection;
import com.flexipgroup.app.config.ConfigurationFile;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.rabbitmq.client.MessageProperties;

public class Receiver {
	
	private ConfigurationFile config;
	
	public Receiver(ConfigurationFile config) {
		this.config = config;
	}
	
	public void consume() {
		Channel channel = RabbitMQConnection.getChannel();
		String TASK_QUEUE_NAME = config.ROUTING_ID;
		String SEP = File.separator;
		
		try {
			channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
			channel.basicQos(1);
			DeliverCallback deliverCallback = (consumerTag, delivery) -> {
				
		        try {
		        	String uniqueID = UUID.randomUUID().toString();
		        	FileUtils fileUtils = new FileUtils();
					String filename = fileUtils.getReceiverFile();
					//String fileUrl = System.getProperty("user.home") + SEP + "flexclient" + SEP + "receiver" + SEP + uniqueID + "." + config.FILE_EXTENSION + ".aes"; 
					FileOutputStream out = new FileOutputStream(filename);
					out.write(delivery.getBody());
					
					//descrypt the file
					SFTPAgent agent = new SFTPAgent(filename, config);
					agent.upload();
					
					// runPostConsumeProcess(out.);
					out.close();
		        } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
		            System.out.println(" [x] Done");
		            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		        }
		    };
		    
			channel.basicConsume(TASK_QUEUE_NAME, false, deliverCallback, consumerTag -> { });
			
			System.out.println("Wrote file to queue");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
