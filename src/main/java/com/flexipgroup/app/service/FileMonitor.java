  
package com.flexipgroup.app.service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileMonitor {
	
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
	
	
	public void executeFileMonitor(String path)
	{
		try {
			monitorFile(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}
	
	public void watchFile (String pathLocation,String uploadedPath) throws InterruptedException
	{
		List<String>fileCollection = new ArrayList<String>();
		
	}
	


}