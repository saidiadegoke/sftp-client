package com.flexipgroup.app.rabbitmq;

import java.io.IOException;

import com.flexipgroup.app.common.FileUtils;
import com.flexipgroup.app.common.RabbitMQConnection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

public class Sender {
	
	private String filePath;
	private String QUEUE;
	
	public Sender(String filePath, String queue) {
		this.filePath = filePath;
		this.QUEUE = queue;
	}
	
	public void send() {
		Channel channel = RabbitMQConnection.getChannel();
		boolean durable = true;
		try {
			channel.queueDeclare(QUEUE, durable, false, false, null);
			byte[] fileBytes = FileUtils.fileToBytes(filePath);
			
			channel.basicPublish("", QUEUE,
	                MessageProperties.PERSISTENT_TEXT_PLAIN,
	                fileBytes);
			
			System.out.println("Wrote file to queue");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
