package com.flexipgroup.app.common;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.poi.EncryptedDocumentException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQConnection {
	
	public static Channel getChannel() {
		ConnectionFactory factory = new ConnectionFactory();
		 
		try {
			 
			 Connection connection = factory.newConnection();
			 return connection.createChannel();

		} catch (EncryptedDocumentException e1) {
			e1.printStackTrace();
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
		 
		return null;
	}

}
