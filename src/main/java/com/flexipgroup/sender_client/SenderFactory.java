package com.flexipgroup.sender_client;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

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
		switch(file.getMimeType()) {
			case MimeType.CSV:
				senderStrategy = new CSVSender(file);
				break;
			case MimeType.XLSX:
				senderStrategy = new XLSXSender(file);
				break;
					
		}
		return senderStrategy;
	}
	
}