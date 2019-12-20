package com.flexipgroup.sender_client;

import java.io.IOException;

import com.flexipgroup.sender_impli.AESSender;
import com.flexipgroup.sender_impli.CSVSender;
import com.flexipgroup.sender_impli.TXTSender;
import com.flexipgroup.sender_impli.XLSXSender;
import com.flexipgroup.sender_strategy.SenderStrategy;


public class SenderFactory {
	
	private MessagingFile file;
	
	SenderFactory(MessagingFile file) {
		this.file = file;
	}
	
	public SenderFactory(String url) {
	}

	public SenderStrategy getInstance() throws IOException {
		SenderStrategy senderStrategy = null;
		String type = file.getMimeType();
		if(type != null) {
			switch(file.getMimeType()) {
				case MimeType.CSV:
					senderStrategy = new CSVSender(file);
					break;
				case MimeType.XLSX:
					senderStrategy = new XLSXSender(file);
					break;
				case MimeType.TXT:
					senderStrategy = new TXTSender();
				default:
					break;
						
			}
		} else if(file.getExtension().equals("aes")) {
			senderStrategy = new AESSender(file);
		} else {
			System.out.println("Log out errors for sysadmin to see.");
			senderStrategy = new TXTSender();
		}
		
		return senderStrategy;
	}
	
}