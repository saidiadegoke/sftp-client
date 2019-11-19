package com.flexipgroup.app.polling;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileMonitor {
	
	public static void main(String[] args) {
		
		String path = System.getProperty("user.home") + File.separator + "sshkeys/tmp/payment.csv";
		try {
			monitorFile(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	private static void monitorFile(File file) throws IOException, InterruptedException {
	    final int POLL_INTERVAL = 1000;
	    while(true) {
		    if(file.exists()) {
			    FileReader reader = new FileReader(file);
			        	System.out.println("File found!");
			        	Thread.sleep(POLL_INTERVAL);
		    } else {
		    	System.out.println("File not found!");
		    	Thread.sleep(POLL_INTERVAL);
		    }
	    }
	}

}
