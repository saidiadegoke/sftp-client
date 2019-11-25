package com.flexipgroup.reciever_client;

import java.io.IOException;

import com.flexipgroup.receiver_impli.XLSXReciever;
import com.flexipgroup.reciever_strategy.RecieverStrategy;
import com.flexipgroup.sender_client.MimeType;


public class RecieverFactory {
private RecieverMessagingFile file;
	
	RecieverFactory(RecieverMessagingFile file) {
		this.file = file;
	}
	
	public RecieverFactory(String url) {
	}

	public RecieverStrategy getInstance() throws IOException {
		RecieverStrategy recieverStrategy = null;
		switch(file.getMimeType()) {
			case MimeType.XLSX:
				recieverStrategy = new XLSXReciever(file);
				break;
	
		}
		return recieverStrategy;
	}
}
