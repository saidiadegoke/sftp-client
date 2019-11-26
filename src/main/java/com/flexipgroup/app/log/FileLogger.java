package com.flexipgroup.app.log;

import org.apache.log4j.Logger;

import com.flexipgroup.app.Application;

public class FileLogger {
	
	   //create an object of FileLogger
	 private static final Logger instance = Logger.getLogger(Application.class);

	 //make the constructor private so that this class cannot be
	   //instantiated and empty constructor
	   public FileLogger(){}

	
	   public static void log(String message, String level) {
		   switch(level) {
		   		case "info":
				instance.info(message);
				break;
				
		   		case "error":
		   			instance.error(message);
				break;
				
		   		case "warn":
		   			instance.warn(message);
				break;
				
		   		case "trace":
		   			instance.trace(message);
				break;
				
		   		case "fatal":
		   			instance.fatal(message);
				break;
				
		   		default:
		   	     System.out.println("");
		   }
	}


}
