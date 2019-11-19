package com.flexipgroup.app.microservices;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RegisterKnownHosts {
	
	public static void main (String [] args)throws Exception
	{
		String hostname="192.168.1.245";
		
		ProcessBuilder processBuilder = new ProcessBuilder();
		
		//Run a shell command
		processBuilder.command("bash", "-c", "ssh-keyscan "+hostname+" >> ~/.ssh/known_hosts");
		
		Process process = processBuilder.start();
			
		StringBuilder output = new StringBuilder();
			
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
		String line;int count = 0;
		
		while((line = reader.readLine())!= null)
		{
			output.append(line);
		}
				
		int exitVal = process.waitFor();
			
		if(exitVal == 0)
		{
			System.out.println("Success!");
			System.out.println(output);
			System.out.println(count);
			//System.exit(0);
		}
		
	}
	
}
