package com.flexipgroup.app.sshconnect;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileIOMethods {
	
	String clientFile = System.getProperty("user.home") + File.separator + "client.txt";
	public static String readfile;
	
	public void creatFile() throws Exception
	{
		File file = new File(clientFile);
		if(file.createNewFile()) {
			//System.out.println("file already exists");
		}else {
			//System.out.println("File already exists");
		}	
	}
	
	
	public void writeFile() throws Exception
	{
		FileWriter writer = new FileWriter(clientFile);
		writer.write(readfile);
		writer.close();
	}
	
	
	public void readFile() throws Exception
	{
		FileReader reader = new FileReader(clientFile);
		int c;String a="";
		while((c = reader.read())!= -1)
			a+=(char)c;
		
		System.out.println(a);
	}

}
