package com.flexipgroup.app.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.ini4j.Wini;

import com.flexipgroup.app.cipher.MyFileVisitor;
import com.flexipgroup.app.log.FileLogger;

public class FileObserver {
	
	public FileObserver() {
		run();
	}
	
	private static String iniPath = System.getProperty("user.home")+File.separator+"/Documents/flexware/sftp-client/config.ini";
	
	public void run(){
		/**
		 * Write code to listen to the folder upload and add the path the 
		 * file found inside it to the instance variable files array
		 * 
		 * Set and get the file path in the config.ini file
		 */
		try
		{
				Wini ini = new Wini(new File(iniPath));
				
				WatchService watchService = FileSystems.getDefault().newWatchService();
				
				String uploadPath = System.getProperty("user.home")+File.separator+ini.get("polling", "upload");
				Path path = Paths.get(uploadPath);
				boolean trigger = false;
				path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,StandardWatchEventKinds.ENTRY_DELETE,StandardWatchEventKinds.ENTRY_MODIFY);
				
				WatchKey watchkey;
				while ((watchkey = watchService.take()) != null)
				{
					for (WatchEvent<?> event : watchkey.pollEvents())
					{
						trigger = !trigger;
						if(!event.context().toString().equals(".DS_Store")) {
							System.out.println("Event kind : " + event.kind() + ". File affected : " + event.context().toString()+"\n\n");
								if(trigger = true)
								{
										MyFileVisitor obj = new MyFileVisitor();
										Files.walkFileTree(path, obj);
								}		
						}
						
					}
					watchkey.reset();
					
					
				}
		
		}catch(IOException ioe)
		{
			FileLogger.log(ioe.toString(),"error");

		}catch(InterruptedException in)
		{
			FileLogger.log(in.toString(),"error");

		}
	
	}
	

}
